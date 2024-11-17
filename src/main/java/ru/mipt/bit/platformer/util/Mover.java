package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.interfaces.AI;
import ru.mipt.bit.platformer.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Movable;
import ru.mipt.bit.platformer.objects.physical.InvisibleAmmunition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Mover {

    private final TileMovement tileMovement;

    public Mover(TileMovement tileMovement) {
        this.tileMovement = tileMovement;
    }

    public static Collection<GameObjectAbt> move(float deltaTime,
                                                 Collection<GameObjectAbt> obstacles,
                                                 Level level,
                                                 Collection<GameObjectAbt> deleteObjects) {

        Collection<GameObjectAbt> obstaclesCopy = new HashSet<>(obstacles);
        for (GameObjectAbt obstacle : obstacles) {
            if (obstacle instanceof Movable movable) {
//                if (movable instanceof AI ai) {
//                    movable.changeMovementState(ai.generateDirection(), obstacles, level);
//                }
//                if (movable instanceof InvisibleAmmunition amo) {
//                    if (amo.existCollisions(amo.getDirection(), obstacles) || amo.outOfBorders(amo.getDirection(), level)) {
//                        deleteObjects.add(amo);
//                        continue;
//                    }
//                    movable.changeMovementState(amo.getDirection(), obstacles, level);
//                }
                Direction direction = movable.getDirection();
                if (!(direction == null)) {
                    movable.setDirection(direction);
                    movable.changeMovementState(direction, obstacles, level);
                    movable.move(deltaTime);
                }
            }
            obstaclesCopy.add(obstacle);
        }
        return obstaclesCopy;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }
}
