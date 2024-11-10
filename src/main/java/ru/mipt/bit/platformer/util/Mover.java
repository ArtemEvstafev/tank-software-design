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

    public static void move(float deltaTime,
                            Collection<? extends Movable> movables,
                            Collection<? extends GameObjectAbt> obstacles,
                            Level level) {
        for (Movable movable : movables) {
            if (movable instanceof AI ai) {
                movable.changeMovementState(ai.generateDirection(), obstacles, level);
            }
            if (movable instanceof InvisibleAmmunition amo) {
                movable.changeMovementState(amo.getDirection(), obstacles, level);
            }
            movable.move(deltaTime);
        }
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }
}
