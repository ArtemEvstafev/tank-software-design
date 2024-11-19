package ru.mipt.bit.platformer.objects.interfaces;

public interface Destroyable extends GameObject {
    int getHealth();
    void getDamage(int damage);
    void destroy();
}
