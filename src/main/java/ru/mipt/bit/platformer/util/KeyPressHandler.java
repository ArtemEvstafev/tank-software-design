package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.keys.Key;

public abstract class KeyPressHandler {
    public static void handleKeyPress(Key ...keys) {
        for (Key k : keys) {
            if (k.isPressed())
                k.action();
        }
    }
}
