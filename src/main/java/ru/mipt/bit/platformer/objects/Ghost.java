package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Ghost implements Movable {

    private final float movementSpeed;
    private final GridPoint2 coordinates;
    private float rotation;
    private final GridPoint2 destinationCoordinates;
    private float movementProgress;

    public Ghost(GridPoint2 coordinates, float movementSpeed, float movementProgress) {
        this.movementProgress = movementProgress;
        this.movementSpeed = movementSpeed;
        this.coordinates = coordinates;
        destinationCoordinates = new GridPoint2(coordinates);
    }

    @Override
    public float getMovementSpeed() {
        return this.movementSpeed;
    }

    @Override
    public GridPoint2 getDestinationCoordinates() {
        return this.destinationCoordinates;
    }

    @Override
    public float getMovementProgress() {
        return this.movementProgress;
    }

    @Override
    public void setMovementProgress(float movementProgress) {
        this.movementProgress = movementProgress;
    }

    @Override
    public void changeDestinationCoordinates(GridPoint2 direction) {
        destinationCoordinates.x += direction.x;
        destinationCoordinates.y += direction.y;
    }

    @Override
    public void move(float deltaTime) {
        setMovementProgress(continueProgress(movementProgress, deltaTime, movementSpeed));
        if (isEqual(movementProgress, 1f)) {
            setCoordinates(destinationCoordinates);
        }
    }

    @Override
    public GridPoint2 getCoordinates() {
        return this.coordinates;
    }

    @Override
    public void setCoordinates(GridPoint2 coordinates) {
        this.coordinates.set(coordinates);
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public float getRotation() {
        return rotation;
    }
}
