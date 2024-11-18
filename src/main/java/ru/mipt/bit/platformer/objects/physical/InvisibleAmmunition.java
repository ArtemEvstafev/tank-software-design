package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;

import java.util.Collection;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class InvisibleAmmunition extends Ghost {

    protected final int damage;

    public InvisibleAmmunition(GridPoint2 coordinates, float movementSpeed, float movementProgress, Direction direction, int damage) {
        super(coordinates, direction.getRotation(), movementSpeed, movementProgress);
        this.direction = direction;
        this.damage = damage;
    }

    @Override
    public void move(float deltaTime, Collection<? extends GameObjectAbt> obstacles, Level level) {
        changeMovementState(obstacles, level);
        setMovementProgress(continueProgress(movementProgress, deltaTime, movementSpeed));
        if (isEqual(movementProgress, 1f)) {
            setCoordinates(destinationCoordinates);
        }
    }

    public boolean anyObstacles(Collection<? extends GameObjectAbt> obstacles, Level level) {
        return existCollisions(direction, obstacles) || outOfBorders(direction, level);
    }
}
