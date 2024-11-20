package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.interfaces.Destroyable;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.physical.InvisibleAmmunition;

import java.util.Collection;

public class Deleter {
    public static void delete(Collection<GameObjectAbt> objects,
                              Level level) {

        for (GameObjectAbt obstacle : objects) {
            if (obstacle instanceof Destroyable destroyable && destroyable.getHealth() <= 0) {
                ObjectsUpdateListener.removeObject(obstacle);
            }
            if (obstacle instanceof InvisibleAmmunition ammo && CollisionsObserver.existAnyCollisions(ammo, objects, level) && ammo.getMovementProgress() == 1) {
                ObjectsUpdateListener.removeObject(obstacle);
            }
        }
        ObjectsUpdateListener.updateObjects(objects);
    }
}
