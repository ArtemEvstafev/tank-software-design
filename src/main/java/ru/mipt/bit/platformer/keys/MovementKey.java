package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.objects.Movable;
import ru.mipt.bit.platformer.objects.GameObject;

import java.util.Arrays;
import java.util.Collection;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public abstract class MovementKey implements Key {

    protected final Collection<? extends GameObject> obstacles;
    private   final Collection<Movable> movables;
    private final int[] keys;
    private final Direction dir;

    public MovementKey(Collection<? extends GameObject> obstacles, Collection<Movable> movables, int[] keys, Direction dir) {
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
            if ((isEqual(movable.getMovementProgress(), 1f))) {
                if (!existCollisions(movable)) {
                    movable.changeDestinationCoordinates(dir.getDirection());
                    movable.setMovementProgress(0f);
                }
                movable.setRotation(dir.getRotation());
            }
        }
    }
}

