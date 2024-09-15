package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.math.GridPoint2;

public interface Key {
    default boolean isPressed(){
        return false;
    };
    default void action(){
        return;
    };
}

