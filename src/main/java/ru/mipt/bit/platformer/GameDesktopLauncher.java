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

import ru.mipt.bit.platformer.objects.Object;
import ru.mipt.bit.platformer.util.Drawer;
import ru.mipt.bit.platformer.util.KeyPressHandler;
import ru.mipt.bit.platformer.util.Mover;
import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.objects.*;
import ru.mipt.bit.platformer.keys.*;


import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {

    private Batch batch;
    private TiledMap level;
    private MapRenderer levelRenderer;
    private TileMovement tileMovement;

    private List<Drawable> drawables;
    private List<Movable>  movables;

    @Override
    public void create() {
        batch = new SpriteBatch();
        level = new TmxMapLoader().load("level.tmx");
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        final TiledMapTileLayer groundLayer = getSingleLayer(level);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        final Tank tank = new Tank
                (
                        new Texture("images/tank_blue.png"),
                        new GridPoint2(1, 1),
                        0.4f,
                        1f,
                        0,
                        tileMovement
                );

        final Tree tree = new Tree
                (
                        new Texture("images/greenTree.png"),
                        new GridPoint2(1, 3)
                );
        tree.placeOnLayer(groundLayer);

        drawables = new ArrayList<>(List.of(tank, tree));
        movables  = new ArrayList<>(List.of(tank));
    }

    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

        KeyPressHandler.handleKeyPress
                (
                        new UP
                        (
                                drawables,
                                movables,
                                new int[]{UP, W},
                                Direction.UP
                        ),
                        new DOWN
                        (
                                drawables,
                                movables,
                                new int[]{DOWN, S},
                                Direction.DOWN
                        ),
                        new LEFT
                        (
                                drawables,
                                movables,
                                new int[]{LEFT, A},
                                Direction.LEFT
                        ),
                        new RIGHT
                        (
                                drawables,
                                movables,
                                new int[]{RIGHT, D},
                                Direction.RIGHT
                        )
                );

        Mover.move(deltaTime, movables);

        // render each tile of the level
        levelRenderer.render();

        batch.begin();

        Drawer.draw(batch, drawables);

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
        Drawer.dispose(drawables);
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
