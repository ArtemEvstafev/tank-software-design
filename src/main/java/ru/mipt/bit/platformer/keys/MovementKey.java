package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.interfaces.AI;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Movable;
import ru.mipt.bit.platformer.objects.interfaces.GameObject;

import java.util.Arrays;
import java.util.Collection;

public class MovementKey implements Key {

    protected final Collection<? extends GameObjectAbt> obstacles;
    private   final Collection<? extends Movable> movables;
    private final int[] keys;
    private final Direction direction;
    private final Level level;

    public MovementKey(Collection<? extends GameObjectAbt> obstacles,
                       Collection<Movable> movables,
                       int[] keys,
                       Direction direction,
                       Level level) {
        this.obstacles = obstacles;
        this.movables  =  movables;
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
        for (Movable movable : movables) {
            if (!(movable instanceof AI) && movable.canMoveToDirection(direction, obstacles, level)) {
                movable.move(direction);
            }
        }
    }
}

