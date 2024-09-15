package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.TileMovement;

public interface Movable extends Object {
    float getMovementSpeed();
    GridPoint2 getDestinationCoordinates();
    float getMovementProgress();
    void setMovementProgress(float movementProgress);
    void setRotation(float rotation);
    void changeDestinationCoordinates(GridPoint2 direction);
    void move(float deltaTime, TileMovement tileMovement);
}
