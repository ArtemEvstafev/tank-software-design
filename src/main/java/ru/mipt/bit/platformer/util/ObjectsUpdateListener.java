package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.physical.InvisibleAmmunition;

import java.util.Collection;
import java.util.HashSet;

public class ObjectsUpdateListener {
    private final static Collection<GameObjectAbt> toAdd    = new HashSet<>();
    private final static Collection<GameObjectAbt> toRemove = new HashSet<>();

    public static void addObject(GameObjectAbt objectAbt) {
        toAdd.add(objectAbt);
    }

    public static void removeObject(GameObjectAbt objectAbt) {
        toRemove.add(objectAbt);
    }

    public static void updateObjects(Collection<GameObjectAbt> destination) {
        for (GameObjectAbt object : toAdd) {
            if (!destination.contains(object)) {
                destination.add(object);
            }
        }
        destination.removeAll(toRemove);
        toAdd   .clear();
        toRemove.clear();
    }

    public static void restruture(Collection<GameObjectAbt> destination) {
        if (destination != null) {
            var tmp = new HashSet<GameObjectAbt>();

            for (GameObjectAbt object : destination) {
                if (!(object instanceof InvisibleAmmunition)) {
                    tmp.add(object);
                }
            }
            for (GameObjectAbt object: destination) {
                if   (object instanceof InvisibleAmmunition) {
                    tmp.add(object);
                }
            }
            destination.clear();
            destination.addAll(tmp);
        }
    }
}
