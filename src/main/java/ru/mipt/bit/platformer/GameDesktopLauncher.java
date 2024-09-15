package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;

import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.objects.Object;
import ru.mipt.bit.platformer.util.Batcher;
import ru.mipt.bit.platformer.util.KeyPressHandler;
import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.objects.*;


import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {

    private Batcher batcher;

    private TiledMap level;
    private MapRenderer levelRenderer;
    private TileMovement tileMovement;

    private Tree tree;
    private Tank tank;

    @Override
    public void create() {
        batcher = new Batcher(new SpriteBatch());

        // load level tiles
        level = new TmxMapLoader().load("level.tmx");
        levelRenderer = createSingleLayerMapRenderer(level, batcher.getBatch());
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

        final  Object[] objects  = {tree};
        final Movable[] movables = {tank};

        if ((isEqual(tank.getMovementProgress(), 1f))) {
            KeyPressHandler.handleKeyPress
                    (
                            new ru.mipt.bit.platformer.keys.UP
                                    (
                                            objects,
                                            movables,
                                            new int[]{UP, W},
                                            Direction.UP
                                    ),
                            new ru.mipt.bit.platformer.keys.DOWN
                                    (
                                            objects,
                                            movables,
                                            new int[]{DOWN, S},
                                            Direction.DOWN
                                    ),
                            new ru.mipt.bit.platformer.keys.LEFT
                                    (
                                            objects,
                                            movables,
                                            new int[]{LEFT, A},
                                            Direction.LEFT
                                    ),
                            new ru.mipt.bit.platformer.keys.RIGHT
                                    (
                                            objects,
                                            movables,
                                            new int[]{RIGHT, D},
                                            Direction.RIGHT
                                    )
                    );
        }

        // calculate interpolated player screen coordinates
        tileMovement.moveRectangleBetweenTileCenters(tank.getRectangle(), tank.getCoordinates(), tank.getDestinationCoordinates(), tank.getMovementProgress());

        tank.setMovementProgress(continueProgress(tank.getMovementProgress(), deltaTime, tank.getMovementSpeed()));
        if (isEqual(tank.getMovementProgress(), 1f)) {
            // record that the player has reached his/her destination
            tank.setCoordinates(tank.getDestinationCoordinates());
        }

        // render each tile of the level
        levelRenderer.render();

        batcher.draw(tank, tree);

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
        tree.dispose();
        tank.dispose();
        level.dispose();
        batcher.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
