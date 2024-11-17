package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.interfaces.AI;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Movable;
import ru.mipt.bit.platformer.objects.physical.InvisibleAmmunition;

import java.util.Arrays;
import java.util.Collection;

public class MovementKey implements Key {

    protected final Collection<? extends GameObjectAbt> objects;
    private final int[] keys;
    private final Direction direction;
    private final Level level;

    public MovementKey(Collection<? extends GameObjectAbt> objects,
                       int[] keys,
                       Direction direction,
                       Level level) {
        this.objects = objects;
        this.keys = keys;
        this.direction = direction;
        this.level = level;
    }

    @Override
    public boolean isPressed() {
        return Arrays.stream(keys).anyMatch(key -> Gdx.input.isKeyPressed(key));
    }

    @Override
    public void doAction() {
        for (GameObjectAbt objectAbt : objects) {
            if (objectAbt instanceof Movable movable) {
                if (!(movable instanceof AI || movable instanceof InvisibleAmmunition)) {
                    movable.setDirection(direction);
//                    movable.changeMovementState(direction, objects, level);
                }
            }
        }
    }
}

