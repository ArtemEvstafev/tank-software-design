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

        Collection<GameObjectAbt> obstaclesCopy = new HashSet<>();
        for (GameObjectAbt obstacle : obstacles) {
            if (obstacle instanceof Movable movable) {
                movable.move(deltaTime, obstacles, level);
            }
            if (!(obstacle instanceof InvisibleAmmunition)) {
                obstaclesCopy.add(obstacle);
            }
        }
        for (GameObjectAbt obstacle : obstacles) {

            if (obstacle instanceof InvisibleAmmunition amo) {
                //                if (!amo.canMove(amo.getDirection(), obstacles, level)) {
                if (amo.existCollisions(amo.getDirection(), obstacles) || amo.outOfBorders(amo.getDirection(), level)) {
//                    deleteObjects.add(amo);
                    continue;
                }
                obstaclesCopy.add(amo);
            }
        }
        return obstaclesCopy;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }
}
