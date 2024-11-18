package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Movable;

import java.util.Collection;

public class Mover {

    private final TileMovement tileMovement;

    public Mover(TileMovement tileMovement) {
        this.tileMovement = tileMovement;
    }

    public static void move(float deltaTime,
                            Collection<GameObjectAbt> obstacles,
                            Level level) {

        for (GameObjectAbt obstacle : obstacles) {
            if (obstacle instanceof Movable movable) {
                movable.move(deltaTime, obstacles, level);
            }
        }
        ObjectsUpdateListener.restruture();
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }
}
