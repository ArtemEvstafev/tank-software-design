package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.math.GridPoint2;

public interface Movable extends Object {
    float getMovementSpeed();
    GridPoint2 getDestinationCoordinates();
    float getMovementProgress();
    void setMovementProgress(float movementProgress);
    void changeDestinationCoordinates(GridPoint2 direction);
    void move(float deltaTime);
}
