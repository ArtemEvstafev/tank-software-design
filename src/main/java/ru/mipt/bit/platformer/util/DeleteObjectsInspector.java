package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.interfaces.Destroyable;
import ru.mipt.bit.platformer.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.physical.InvisibleAmmunition;

import java.util.Collection;

import static com.badlogic.gdx.utils.JsonValue.ValueType.object;

public class DeleteObjectsInspector {
    public static void inspect(Collection<GameObjectAbt> objects,
                               Level level) {

        for (GameObjectAbt obstacle : objects) {
            if (obstacle instanceof Destroyable destroyable && destroyable.getHealth() <= 0) {
                ObjectsUpdateListener.removeObject(obstacle);
            }
            if (obstacle instanceof InvisibleAmmunition ammo && CollisionsObserver.existAnyCollisions(ammo, objects, level)) {
                ObjectsUpdateListener.removeObject(obstacle);
            }
        }
        ObjectsUpdateListener.updateObjects(objects);
    }
}
