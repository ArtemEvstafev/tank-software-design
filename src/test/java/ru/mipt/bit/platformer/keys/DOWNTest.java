package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.objects.Ghost;
import ru.mipt.bit.platformer.objects.Movable;
import ru.mipt.bit.platformer.objects.Object;
import ru.mipt.bit.platformer.objects.Wall;

import static com.badlogic.gdx.Input.Keys.DOWN;
import static com.badlogic.gdx.Input.Keys.S;
import static org.junit.jupiter.api.Assertions.*;

class DOWNTest {
    @Test
    void existCollisions() {
        Wall wall = new Wall
                (
                        new GridPoint2(2, 2)
                );
        Ghost ghost = new Ghost
                (
                        new GridPoint2(2, 3),
                        1f,
                        1f
                );

        ru.mipt.bit.platformer.objects.Object[] objects = {wall};
        Movable[] movables = {ghost};

        DOWN down = new DOWN
                (
                        objects,
                        movables,
                        new int[]{S, DOWN},
                        Direction.DOWN
                );
        assertTrue(down.existCollisions(ghost));
    }

    @Test
    void notExistCollisions() {
        Wall wall = new Wall
                (
                        new GridPoint2(2, 2)
                );
        Ghost ghost = new Ghost
                (
                        new GridPoint2(2, 1),
                        1f,
                        1f
                );

        Object[] objects = {wall};
        Movable[] movables = {ghost};

        DOWN down = new DOWN
                (
                        objects,
                        movables,
                        new int[]{S, DOWN},
                        Direction.DOWN
                );
        assertFalse(down.existCollisions(ghost));
    }
}