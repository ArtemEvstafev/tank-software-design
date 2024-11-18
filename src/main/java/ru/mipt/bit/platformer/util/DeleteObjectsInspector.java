package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.physical.InvisibleAmmunition;

import java.util.Collection;

public class DeleteObjectsInspector {
    public static void inspect(Collection<GameObjectAbt> obstacles,
                               Level level) {

        for (GameObjectAbt obstacle : obstacles) {
            if (obstacle instanceof InvisibleAmmunition ammo && ammo.anyObstacles(obstacles, level)) {
                ObjectsUpdateListener.removeObject(ammo);
            }
        }
        ObjectsUpdateListener.updateObjects();
    }
}
