package ru.mipt.bit.platformer.keys;

import ru.mipt.bit.platformer.objects.Movable;
import ru.mipt.bit.platformer.objects.Object;

import java.util.Arrays;

import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedY;

public class UP extends MovementKey {

    public UP(Object[] obstacles, Movable[] movables, int[] keys, Direction dir) {
        super(obstacles, movables, keys, dir);
    }

    @Override
    boolean existCollisions(Movable movable) {
        return Arrays.stream(obstacles).anyMatch(obstacle -> obstacle.getCoordinates().equals(incrementedY(movable.getCoordinates())));
    }
}
