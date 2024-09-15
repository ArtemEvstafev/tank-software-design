package ru.mipt.bit.platformer.keys;

public interface Key {
    default boolean isPressed(){
        return false;
    };
    default void action(){
        return;
    };
}

