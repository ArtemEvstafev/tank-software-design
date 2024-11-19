package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Movable;

import java.util.Collection;

public class CollisionsObserver {
    public static boolean existObjectsCollisions(Movable object, Collection<? extends GameObjectAbt> obstacles) {
        return obstacles.stream().anyMatch((obstacle) -> doCollide(object, obstacle));
    }

    private static boolean doCollide(Movable object, GameObjectAbt obstacle) {
        if (!(object == obstacle)) {
            GridPoint2 directionGP = object.getDirection().getGridPoint();
            var destCoordinates = object.getCoordinates().cpy().add(directionGP);
            if (obstacle instanceof Movable movable) {
                return movable.getCoordinates().equals(destCoordinates)
                        || movable.getDestinationCoordinates().equals(destCoordinates);
            }
            return obstacle.getCoordinates().equals(destCoordinates);
        }
        return false;
    }

    public static boolean existObjectsCollisions(GameObjectAbt object, Collection<? extends GameObjectAbt> obstacles) {
        if (object instanceof Movable movable) {
            return existObjectsCollisions(movable, obstacles);
        }
        return obstacles.stream().anyMatch((obstacle) -> doCollide(object, obstacle));
    }

    private static boolean doCollide(GameObjectAbt object, GameObjectAbt obstacle) {
        if (object instanceof Movable movable) {
            return doCollide(movable, obstacle);
        }
        if (!(object == obstacle)) {
            var coordinates = object.getCoordinates();
            if (obstacle instanceof Movable movable) {
                GridPoint2 currentCoordinates = movable.getCoordinates();
                GridPoint2 destionCoordinates = currentCoordinates.cpy().add(movable.getDirection().getGridPoint());
                return destionCoordinates.equals(coordinates);
            }
            return false;
        }
        return false;
    }

    public static boolean existLevelBorderCollisions(Movable object, Level level) {
        GridPoint2 destCoordinates = new GridPoint2(object.getCoordinates()).add(object.getDirection().getGridPoint());
        return (destCoordinates.x > level.getWidth() - 1 || destCoordinates.y > level.getHeight() - 1) ||
                (destCoordinates.x < 0 || destCoordinates.y < 0);
    }

    public static boolean existLevelBorderCollisions(GameObjectAbt object, Level level) {
        if (object instanceof Movable movable) {
            return existLevelBorderCollisions(movable, level);
        }
        GridPoint2 coordinates = new GridPoint2(object.getCoordinates());
        return (coordinates.x > level.getWidth() - 1 || coordinates.y > level.getHeight() - 1) ||
               (coordinates.x < 0 || coordinates.y < 0);
    }

    public static boolean existAnyCollisions(GameObjectAbt object, Collection<? extends GameObjectAbt> obstacles, Level level) {
        return existObjectsCollisions(object, obstacles) || existLevelBorderCollisions(object, level);
    }

    public static GameObjectAbt collideWithObject(Movable object, Collection<? extends GameObjectAbt> obstacles) {
        for (GameObjectAbt obstacle : obstacles) {
            if (doCollide(object, obstacle)) {
                return obstacle;
            }
        }
        return null;
    }
}
