package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.math.GridPoint2;

public interface Object {
    GridPoint2 getCoordinates();
    void setCoordinates(GridPoint2 coordinates);
}
