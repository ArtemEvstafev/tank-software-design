package ru.mipt.bit.platformer
        ;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.keys.*;
import ru.mipt.bit.platformer.levels.DrawableLevel;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.util.*;
import ru.mipt.bit.platformer.util.commandaManagers.CommandsCreator;
import ru.mipt.bit.platformer.util.commandaManagers.CommandsExecuter;
import ru.mipt.bit.platformer.util.commandaManagers.CommandsGenerator;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

@Component
public class Game {

    private final Batch batch;
    private final DrawableLevel level;
    private final Collection<GameObjectAbt> allObjects;
    private final Key[] keys;
    private Queue<Command> commands;

    @Autowired
    public Game(Batch batch, DrawableLevel level, Collection<GameObjectAbt> allObjects, Key[] keys) {
        this.batch = batch;
        this.level = level;
        this.allObjects = allObjects;
        this.keys = keys;
    }

    public void render() {
        commands = CommandsCreator.createCommands(new KeyPressHandler(keys));
        CommandsExecuter.executeCommands(commands);
        Mover.move(Gdx.graphics.getDeltaTime(), allObjects, level);
        Drawer.draw(level, batch, allObjects);
        Deleter.delete(allObjects, level);
    }

    public void dispose() {
        Drawer.disposeAll(allObjects, level, batch);
    }
}