package ru.mipt.bit.platformer.objects.interfaces;

import ru.mipt.bit.platformer.keys.Direction;

public interface AI extends GameObject {
    Direction generateDirection();
}
