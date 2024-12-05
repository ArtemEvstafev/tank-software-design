package ru.mipt.bit.platformer
        ;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.keys.*;
import ru.mipt.bit.platformer.levels.DrawableLevel;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.util.Deleter;
import ru.mipt.bit.platformer.util.Drawer;
import ru.mipt.bit.platformer.util.KeyPressHandler;
import ru.mipt.bit.platformer.util.Mover;

import java.util.Collection;

@Component
public class Game {

    private final Batch batch;
    private final DrawableLevel level;
    private final Collection<GameObjectAbt> allObjects;
    private final Key[] keys;

    @Autowired
    public Game(Batch batch, DrawableLevel level, Collection<GameObjectAbt> allObjects, Key[] keys) {
        this.batch = batch;
        this.level = level;
        this.allObjects = allObjects;
        this.keys = keys;
    }

    public void render() {
        KeyPressHandler.handleKeyPress(keys);
        Mover.move(Gdx.graphics.getDeltaTime(), allObjects, level);
        Drawer.drawAll(level, batch, allObjects);
        Deleter.delete(allObjects, level);
    }

    public void dispose() {
        Drawer.disposeAll(allObjects, level, batch);
    }

    public void startGame() {
        System.out.println("Game started!");
    }
}