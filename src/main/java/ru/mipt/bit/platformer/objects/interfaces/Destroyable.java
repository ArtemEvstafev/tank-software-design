package ru.mipt.bit.platformer.objects.interfaces;

public interface Destroyable {
    void setHealth(int health);
    int getHealth();
    void destroy();
}
