package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.objects.Movable;

import java.util.List;

public class Mover {
    public static void move(float deltaTime, final List<Movable> movables) {
        for (Movable movable : movables) {
            movable.move(deltaTime);
        }
    }
}
