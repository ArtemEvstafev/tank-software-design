package ru.mipt.bit.platformer.keys;

import ru.mipt.bit.platformer.objects.Movable;
import ru.mipt.bit.platformer.objects.Object;

import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.decrementedY;

public class DOWN extends MovementKey {

    public DOWN(List<? extends Object> obstacles, List<Movable> movables, int[] keys, Direction dir) {
        super(obstacles, movables, keys, dir);
    }

    @Override
    boolean existCollisions(Movable movable) {
        return obstacles.stream().anyMatch(obstacle -> obstacle.getCoordinates().equals(decrementedY(movable.getCoordinates())));
    }
}
