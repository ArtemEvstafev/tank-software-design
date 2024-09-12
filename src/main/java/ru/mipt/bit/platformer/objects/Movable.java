package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.math.GridPoint2;

public interface Movable extends Object {
    float getMovementSpeed();
    GridPoint2 getDestinationCoordinates();
    float getMovementProgress();
    void setMovementProgress(float movementProgress);
    void setRotation(float rotation);
    void changeDestinationCoordinates(int number, boolean toX);
}
