package ru.mipt.bit.platformer.util;

import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.physical.InvisibleAmmunition;

import java.util.Collection;
import java.util.HashSet;

public class ObjectsUpdateListener {
    private       static Collection<GameObjectAbt> allObjects;
    private final static Collection<GameObjectAbt> toAdd    = new HashSet<>();
    private final static Collection<GameObjectAbt> toRemove = new HashSet<>();

    public ObjectsUpdateListener(Collection<GameObjectAbt> allObjects) {
        this.allObjects = allObjects;
    }

    public static void addObject(GameObjectAbt objectAbt) {
        if (!allObjects.contains(objectAbt)) {
            toAdd.add(objectAbt);
        }
    }

    public static void removeObject(GameObjectAbt objectAbt) {
        toRemove.add(objectAbt);
    }

    public static void updateObjects() {
        if(allObjects.   addAll(toAdd   )){
            System.out.println("add");
        }
        allObjects.removeAll(toRemove);
        toAdd   .clear();
        toRemove.clear();
    }

    public static void restruture() {
        Collection<GameObjectAbt> tmp = new HashSet<>(allObjects);
        allObjects.addAll(tmp);
    }
}
