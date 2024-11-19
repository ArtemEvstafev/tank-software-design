package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;

import ru.mipt.bit.platformer.levels.DrawableLevel;
import ru.mipt.bit.platformer.objects.interfaces.*;
import ru.mipt.bit.platformer.objects.physical.ShowHealthBarDecorator;
import ru.mipt.bit.platformer.objects.physical.Tank;
import ru.mipt.bit.platformer.util.*;
import ru.mipt.bit.platformer.keys.*;
import ru.mipt.bit.platformer.util.gameLoaders.FromFileGameLoader;
import ru.mipt.bit.platformer.util.gameLoaders.GameLoader;
import ru.mipt.bit.platformer.util.gameLoaders.RandomGeneratedGameLoader;


import java.util.*;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {

    private Batch batch = null;
    private DrawableLevel level = null;

    private Collection<GameObjectAbt> allObjects = new HashSet<>();

    @Override
    public void create() {
        GameLoader gameLoader = new RandomGeneratedGameLoader();
//        GameLoader gameLoader = new FromFileGameLoader();
        batch      = gameLoader.getBatch();
        level      = gameLoader.getLevel();
        allObjects = gameLoader.getObjects();
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
                        new MovementKey
                                (
                                        allObjects,
                                        new int[]{UP, W},
                                        Direction.UP
                                ),
                        new MovementKey
                                (
                                        allObjects,
                                        new int[]{DOWN, S},
                                        Direction.DOWN
                                ),
                        new MovementKey
                                (
                                        allObjects,
                                        new int[]{LEFT, A},
                                        Direction.LEFT
                                ),
                        new MovementKey
                                (
                                        allObjects,
                                        new int[]{RIGHT, D},
                                        Direction.RIGHT
                                ),
                        new HealthToggleKey
                                (
                                        new int[]{L}
                                ),
                        new ShootKey
                                (
                                        new int[]{SPACE},
                                        allObjects
                                )
                );

        Mover.move(deltaTime, allObjects, level);

        DeleteObjectsInspector.inspect(allObjects, level);


        // render each tile of the level
        level.render();

        batch.begin();

        Drawer.draw(batch, allObjects);


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
        Drawer.dispose(allObjects);
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
