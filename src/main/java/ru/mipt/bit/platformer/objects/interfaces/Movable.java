package ru.mipt.bit.platformer.objects.interfaces;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.levels.Level;

import java.util.Collection;

public interface Movable extends GameObject {

    GridPoint2 getDestinationCoordinates();

    void setDirection(Direction direction);

    Direction getDirection();

    void move(float deltaTime, Collection<? extends GameObjectAbt> obstacles, Level level);

    void changeMovementState(Collection<? extends GameObjectAbt> obstacles, Level level);

    boolean canMove(Collection<? extends GameObjectAbt> obstacles, Level level);
}
