package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.objects.Movable;
import ru.mipt.bit.platformer.objects.Object;

import java.util.Arrays;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public abstract class MovementKey implements Key {

    protected final List<? extends Object> obstacles;
    private final List<Movable> movables;
    private final int[] keys;
    private final Direction dir;

    public MovementKey(List<? extends Object> obstacles, List<Movable> movables, int[] keys, Direction dir) {
        this.obstacles = obstacles;
        this.movables  =  movables;
        this.keys = keys;
        this.dir  =  dir;
    }

    @Override
    public boolean isPressed() {
        return Arrays.stream(keys).anyMatch(key -> Gdx.input.isKeyPressed(key));
    }

    abstract boolean existCollisions(final Movable movable);

    @Override
    public void action() {
        for (Movable movable : movables) {
            if ((isEqual(movable.getMovementProgress(), 1f)) && !existCollisions(movable)) {
                movable.changeDestinationCoordinates(dir.getDirection());
                movable.setRotation(dir.getRotation());
                movable.setMovementProgress(0f);
            }
        }
    }
}

