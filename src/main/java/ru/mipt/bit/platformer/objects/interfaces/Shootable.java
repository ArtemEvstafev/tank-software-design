package ru.mipt.bit.platformer.objects.interfaces;

import ru.mipt.bit.platformer.objects.physical.InvisibleAmmunition;

public interface Shootable extends GameObject {
    InvisibleAmmunition shoot();
}
