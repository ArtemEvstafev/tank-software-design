package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;

import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.objects.*;


import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {

//    private static final float MOVEMENT_SPEED = 0.4f;

    private Batch batch;

    private TiledMap level;
    private MapRenderer levelRenderer;
    private TileMovement tileMovement;

    private Tree tree;
    private Tank tank;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // load level tiles
        level = new TmxMapLoader().load("level.tmx");
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        TiledMapTileLayer groundLayer = getSingleLayer(level);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        tank = new Tank
                (
                        new Texture("images/tank_blue.png"),
                        new GridPoint2(1, 1),
                        0.4f,
                        1f,
                        0
                );

        tree = new Tree
                (
                        new Texture("images/greenTree.png"),
                        new GridPoint2(1, 3)
                );

        tree.moveTreeAtTileCenter(groundLayer);

    }

    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            if (isEqual(tank.getPlayerMovementProgress(), 1f)) {
                // check potential player destination for collision with obstacles
                if (!tree.getTreeObstacleCoordinates().equals(incrementedY(tank.getPlayerCoordinates()))) {
                    tank.addPlayerDestinationCoordinates(1, false);
                    tank.setPlayerMovementProgress(0f);
                }
                tank.setPlayerRotation(90f);
            }
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            if (isEqual(tank.getPlayerMovementProgress(), 1f)) {
                if (!tree.getTreeObstacleCoordinates().equals(decrementedX(tank.getPlayerCoordinates()))) {
                    tank.addPlayerDestinationCoordinates(-1, true);
                    tank.setPlayerMovementProgress(0f);
                }
                tank.setPlayerRotation(-180f);
            }
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            if (isEqual(tank.getPlayerMovementProgress(), 1f)) {
                if (!tree.getTreeObstacleCoordinates().equals(decrementedY(tank.getPlayerCoordinates()))) {
                    tank.addPlayerDestinationCoordinates(-1, false);
                    tank.setPlayerMovementProgress(0f);
                }
                tank.setPlayerRotation(-90f);
            }
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            if (isEqual(tank.getPlayerMovementProgress(), 1f)) {
                if (!tree.getTreeObstacleCoordinates().equals(incrementedX(tank.getPlayerCoordinates()))) {
                    tank.addPlayerDestinationCoordinates(1, true);
                    tank.setPlayerMovementProgress(0f);
                }
                tank.setPlayerRotation(0f);
            }
        }

        // calculate interpolated player screen coordinates
        tileMovement.moveRectangleBetweenTileCenters(tank.getPlayerRectangle(), tank.getPlayerCoordinates(), tank.getPlayerDestinationCoordinates(), tank.getPlayerMovementProgress());

        tank.setPlayerMovementProgress(continueProgress(tank.getPlayerMovementProgress(), deltaTime, tank.getMOVEMENT_SPEED()));
        if (isEqual(tank.getPlayerMovementProgress(), 1f)) {
            // record that the player has reached his/her destination
            tank.setPlayerCoordinates(tank.getPlayerDestinationCoordinates());
        }

        // render each tile of the level
        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();

        // render player
        drawTextureRegionUnscaled(batch, tank.getPlayerGraphics(), tank.getPlayerRectangle(), tank.getPlayerRotation());

        // render tree obstacle
        drawTextureRegionUnscaled(batch, tree.getTreeObstacleGraphics(), tree.getTreeObstacleRectangle(), 0f);

        // submit all drawing requests
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        tree.disposeTexture();
        tank.disposeTexture();
        level.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
