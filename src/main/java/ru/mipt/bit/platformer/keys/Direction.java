package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction{
    UP   (new GridPoint2( 0,  1),   90f),
    DOWN (new GridPoint2( 0, -1),  -90f),
    LEFT (new GridPoint2(-1,  0), -180f),
    RIGHT(new GridPoint2( 1,  0),    0f);
    private final GridPoint2 direction;
    private final float rotation;

    Direction(GridPoint2 direction, float rotation) {
        this.direction = direction;
        this.rotation  = rotation;
    }

    public GridPoint2 getDirection() {
        return direction;
    }

    public float getRotation() {
        return rotation;
    }
}
