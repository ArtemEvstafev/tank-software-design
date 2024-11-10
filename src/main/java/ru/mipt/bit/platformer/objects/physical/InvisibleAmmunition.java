package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;

import java.util.Collection;

public class InvisibleAmmunition extends Ghost {

    protected final Direction direction;

    public InvisibleAmmunition(GridPoint2 coordinates, float movementSpeed, float movementProgress, Direction direction) {
        super(coordinates, movementSpeed, movementProgress);
        this.rotation = direction.getRotation();
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void changeMovementState(Direction direction, Collection<? extends GameObjectAbt> obstacles, Level level) {
        if(canMoveToDirection(direction, obstacles, level)) {
            changeDestinationCoordinates(direction.getDirection());
        }
    }
}
