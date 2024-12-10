package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.commands.MoveCommand;
import ru.mipt.bit.platformer.objects.interfaces.AI;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Movable;
import ru.mipt.bit.platformer.objects.physical.InvisibleAmmunition;

import java.util.*;

public class MovementKey implements Key {

    protected final Collection<? extends GameObjectAbt> objects;
    private final int[] keys;
    private final Direction direction;

    public MovementKey(Collection<? extends GameObjectAbt> objects,
                       int[] keys,
                       Direction direction) {
        this.objects = objects;
        this.keys = keys;
        this.direction = direction;
    }

    @Override
    public boolean isPressed() {
        return Arrays.stream(keys).anyMatch(key -> Gdx.input.isKeyPressed(key));
    }

    @Override
    public Queue<Command> makeCommands() {
        Queue<Command> commands = new ArrayDeque<>();
        for (GameObjectAbt objectAbt : objects) {
            if (objectAbt instanceof Movable movable) {
                if (!(movable instanceof AI || movable instanceof InvisibleAmmunition)) {
                    commands.offer(new MoveCommand(movable, direction));
                }
            }
        }
        return commands;
    }
}

