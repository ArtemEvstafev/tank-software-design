package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.math.GridPoint2;

public class InvisibleAmmunition extends Ghost {

    public InvisibleAmmunition(GridPoint2 coordinates, float movementSpeed, float movementProgress) {
        super(coordinates, movementSpeed, movementProgress);
    }
}
