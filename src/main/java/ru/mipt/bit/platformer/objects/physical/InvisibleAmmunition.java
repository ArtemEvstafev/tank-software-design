package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;

import java.util.Collection;

public class InvisibleAmmunition extends Ghost {

    protected final int damage;

    public InvisibleAmmunition(GridPoint2 coordinates, float movementSpeed, float movementProgress, Direction direction, int damage) {
        super(coordinates, direction.getRotation(), movementSpeed, movementProgress);
        this.direction = direction;
        this.damage = damage;
    }
}
