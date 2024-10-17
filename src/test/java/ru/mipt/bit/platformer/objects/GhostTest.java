package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GhostTest {

    @Test
    void changeDestinationCoordinates() {
        Ghost ghost = new Ghost
                (
                        new GridPoint2(2, 3),
                        1f,
                        1f
                );
        ghost.changeDestinationCoordinates(new GridPoint2(3, 7));
        assertEquals(new GridPoint2(5, 10), ghost.getDestinationCoordinates());
    }

    @Test
    void move() {
        Ghost ghost = new Ghost
                (
                        new GridPoint2(2, 3),
                        1f,
                        1f
                );
        ghost.changeDestinationCoordinates(new GridPoint2(3, 7));
        ghost.move(2f);
        assertEquals(new GridPoint2(5, 10), ghost.getCoordinates());
    }
    @Test
    void dontMoveNoTime() {
        Ghost ghost = new Ghost
                (
                        new GridPoint2(2, 3),
                        1f,
                        0f
                );
        ghost.changeDestinationCoordinates(new GridPoint2(3, 7));
        ghost.move(0f);
        assertEquals(new GridPoint2(2, 3), ghost.getCoordinates());

    }

    @Test
    void dontMoveTooFast() {
        Ghost ghost = new Ghost
                (
                        new GridPoint2(2, 3),
                        2f,
                        0f
                );
        ghost.changeDestinationCoordinates(new GridPoint2(3, 7));
        ghost.move(1f);
        assertEquals(new GridPoint2(2, 3), ghost.getCoordinates());

    }
}