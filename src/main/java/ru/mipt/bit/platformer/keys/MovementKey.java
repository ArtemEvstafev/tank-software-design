package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.Movable;
import ru.mipt.bit.platformer.objects.Object;

import java.util.Arrays;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

enum Direction{
    UP   (new GridPoint2( 0,  1),   90f),
    DOWN (new GridPoint2( 0, -1),  -90f),
    LEFT (new GridPoint2(-1,  0), -180f),
    RIGHT(new GridPoint2( 1,  0),    0f);
    private final GridPoint2 direction;
    private final float rotation;

    Direction(GridPoint2 direction, float rotation) {
        this.direction = direction;
        this.rotation  = rotation;
    }

    public GridPoint2 getDirection() {
        return direction;
    }

    public float getRotation() {
        return rotation;
    }
}

public abstract class MovementKey implements Key {

    protected final Object[] obstacles;
    private final Movable[] movables;
    private final int[] keys;
    private final Direction dir;

    public MovementKey(Object[] obstacles, Movable[] movables, int[] keys, Direction dir) {
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
            if (!existCollisions(movable)) {
                movable.changeDestinationCoordinates(dir.getDirection());
                movable.setRotation(dir.getRotation());
            }
        }
    }
}

class UP extends MovementKey {

    public UP(Object[] obstacles, Movable[] movables, int[] keys, Direction dir) {
        super(obstacles, movables, keys, dir);
    }

    @Override
    boolean existCollisions(Movable movable) {
        return Arrays.stream(obstacles).anyMatch(obstacle -> obstacle.getCoordinates().equals(incrementedY(movable.getCoordinates())));
    }
}

class LEFT extends MovementKey {

    public LEFT(Object[] obstacles, Movable[] movables, int[] keys, Direction dir) {
        super(obstacles, movables, keys, dir);
    }

    @Override
    boolean existCollisions(Movable movable) {
        return Arrays.stream(obstacles).anyMatch(obstacle -> obstacle.getCoordinates().equals(decrementedX(movable.getCoordinates())));
    }
}

class DOWN extends MovementKey {

    public DOWN(Object[] obstacles, Movable[] movables, int[] keys, Direction dir) {
        super(obstacles, movables, keys, dir);
    }

    @Override
    boolean existCollisions(Movable movable) {
        return Arrays.stream(obstacles).anyMatch(obstacle -> obstacle.getCoordinates().equals(decrementedY(movable.getCoordinates())));
    }
}

class RIGHT extends MovementKey {

    public RIGHT(Object[] obstacles, Movable[] movables, int[] keys, Direction dir) {
        super(obstacles, movables, keys, dir);
    }

    @Override
    boolean existCollisions(Movable movable) {
        return Arrays.stream(obstacles).anyMatch(obstacle -> obstacle.getCoordinates().equals(incrementedX(movable.getCoordinates())));
    }
}

