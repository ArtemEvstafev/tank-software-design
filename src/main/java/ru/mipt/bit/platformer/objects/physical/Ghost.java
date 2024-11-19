package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Movable;
import ru.mipt.bit.platformer.util.CollisionsObserver;

import java.util.Collection;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Ghost extends GameObjectAbt implements Movable {

    protected final float movementSpeed;
    protected final GridPoint2 destinationCoordinates;
    protected float movementProgress;
    protected Direction direction = null;

    public Ghost(GridPoint2 coordinates, float rotation, float movementSpeed, float movementProgress) {
        super(coordinates, rotation);
        this.movementProgress = movementProgress;
        this.movementSpeed = movementSpeed;
        destinationCoordinates = new GridPoint2(coordinates);
    }

    public Ghost(GridPoint2 coordinates, float movementSpeed, float movementProgress) {
        this(coordinates, 0f, movementSpeed, movementProgress);
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public void setMovementProgress(float movementProgress) {
        this.movementProgress = movementProgress;
    }

    public void changeDestinationCoordinates(GridPoint2 direction) {
        destinationCoordinates.x += direction.x;
        destinationCoordinates.y += direction.y;
    }

    @Override
    public boolean canMove(Collection<? extends GameObjectAbt> obstacles, Level level) {
        return isEqual(movementProgress, 1f) && !CollisionsObserver.existAnyCollisions(this, obstacles, level);
    }

    private void rotateIfPossible() {
        if (isEqual(movementProgress, 1f)) {
            setRotation(direction.getRotation());
        }
    }

    @Override
    public void changeMovementState(Collection<? extends GameObjectAbt> obstacles, Level level) {
        if (direction != null) {
            rotateIfPossible();
            if (canMove(obstacles, level)) {
                changeDestinationCoordinates(direction.getGridPoint());
                setMovementProgress(0f);
            }
        }
    }

    @Override
    public void move(float deltaTime, Collection<? extends GameObjectAbt> obstacles, Level level) {
        changeMovementState(obstacles, level);
        setMovementProgress(continueProgress(movementProgress, deltaTime, movementSpeed));
        if (isEqual(movementProgress, 1f)) {
            setCoordinates(destinationCoordinates);
            this.direction = null;
        }
    }
}
