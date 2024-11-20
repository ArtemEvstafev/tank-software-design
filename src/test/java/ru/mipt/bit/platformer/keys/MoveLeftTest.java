package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.levels.BorderLevel;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.physical.Ghost;
import ru.mipt.bit.platformer.objects.physical.Wall;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoveLeftTest {

    @Test
    void existCollisions() {
        Wall wall = new Wall
                (
                        new GridPoint2(2, 2)
                );
        Ghost ghost = new Ghost
                (
                        new GridPoint2(3, 2),
                        1f,
                        1f
                );
        ghost.setDirection(Direction.LEFT);
        List<GameObjectAbt>   objects = new ArrayList<>(List.of( wall));
        assertFalse(ghost.canMove(objects, new BorderLevel(7, 5)));
    }

    @Test
    void notExistCollisions() {
        Wall wall = new Wall
                (
                        new GridPoint2(2, 2)
                );
        Ghost ghost = new Ghost
                (
                        new GridPoint2(1, 2),
                        1f,
                        1f
                );
        ghost.setDirection(Direction.LEFT);
        List<GameObjectAbt>   objects = new ArrayList<>(List.of( wall));
        assertTrue(ghost.canMove(objects, new BorderLevel(7, 5)));
    }
}