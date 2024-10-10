package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.math.GridPoint2;

public class Wall implements GameObject {

    private final GridPoint2 coordinates;
    private final float rotation = 0f;

    public Wall(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    @Override
    public void setCoordinates(GridPoint2 coordinates) {
        coordinates.set(coordinates);
    }

    @Override
    public void setRotation(float rotation) {}

    @Override
    public float getRotation() {
        return rotation;
    }
}
