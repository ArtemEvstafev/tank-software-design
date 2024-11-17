package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Movable;

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

    @Override
    public float getMovementSpeed() {
        return movementSpeed;
    }

    @Override
    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    @Override
    public float getMovementProgress() {
        return movementProgress;
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
    public boolean canMove(Direction direction, Collection<? extends GameObjectAbt> obstacles, Level level) {
        return isEqual(movementProgress, 1f) && !(existCollisions(direction, obstacles) || outOfBorders(direction, level));
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
            if (canMove(direction, obstacles, level)) {
                changeDestinationCoordinates(direction.getDirection());
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

    public boolean outOfBorders(Direction direction, Level level) {
        GridPoint2 destCoordinates = new GridPoint2(coordinates).add(direction.getDirection());
        return (destCoordinates.x > level.getWidth() - 1 || destCoordinates.y > level.getHeight() - 1) ||
                (destCoordinates.x < 0 || destCoordinates.y < 0);
    }

    public boolean existCollisions(Direction direction, Collection<? extends GameObjectAbt> obstacles) {
        GridPoint2 directionGP = direction.getDirection();
        return obstacles.stream().anyMatch(
                (obstacle) ->
                {
                    if (!(this == obstacle)) {
                        if (obstacle instanceof Movable movable) {

                            return movable.getCoordinates().equals(coordinates.cpy().add(directionGP))
                                    || movable.getDestinationCoordinates().equals(coordinates.cpy().add(directionGP));
                        }
                        return obstacle.getCoordinates().equals(coordinates.cpy().add(directionGP));
                    }
                    return false;
                });
    }

}
