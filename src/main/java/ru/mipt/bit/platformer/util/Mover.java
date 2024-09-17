package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.objects.Movable;

public class Mover {
    public static void move(float deltaTime, final Movable... movables) {
        for (Movable movable : movables) {
            movable.move(deltaTime);
        }
    }
}
