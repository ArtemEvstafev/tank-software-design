package ru.mipt.bit.platformer.objects.interfaces;

public interface Destroyable extends GameObject {
    void setHealth(int health);
    int getHealth();
    void destroy();
}
