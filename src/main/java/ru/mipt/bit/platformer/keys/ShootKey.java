package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.commands.ShootCommand;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Shootable;
import ru.mipt.bit.platformer.util.ObjectsUpdateListener;

import java.util.*;

public class ShootKey implements Key {

    private final int[] keys;
    private final Collection<GameObjectAbt> allObjects;

    public ShootKey(int[] keys, Collection<GameObjectAbt> allObjects) {
        this.keys = keys;
        this.allObjects = allObjects;
    }

    @Override
    public boolean isPressed() {
        return Arrays.stream(keys).anyMatch(key -> Gdx.input.isKeyJustPressed(key));
    }

    @Override
    public Queue<Command> makeCommands() {
        Queue<Command> commands = new ArrayDeque<>();
        for (GameObjectAbt object : allObjects) {
            if (object instanceof Shootable shootable) {
                commands.offer(new ShootCommand(shootable));
            }
        }
        ObjectsUpdateListener.updateObjects(allObjects);
        return commands;
    }
}
