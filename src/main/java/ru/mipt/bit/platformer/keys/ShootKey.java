package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Shootable;
import ru.mipt.bit.platformer.objects.physical.Ammunition;
import ru.mipt.bit.platformer.objects.physical.InvisibleAmmunition;
import ru.mipt.bit.platformer.util.ObjectsUpdateListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class ShootKey implements Key {

    private final int[] keys;
    private final Collection<GameObjectAbt> allObjects;

    public ShootKey(int[] keys, Collection<GameObjectAbt> allObjects) {
        this.keys = keys;
        this.allObjects = allObjects;
    }

    @Override
    public boolean isPressed() {
        return Arrays.stream(keys).anyMatch(key -> Gdx.input.isKeyJustPressed(key));
    }

    @Override
    public void doAction() {
        for (GameObjectAbt object : allObjects) {
            if (object instanceof Shootable shootable) {
                ObjectsUpdateListener.addObject(shootable.shoot());
            }
        }
        ObjectsUpdateListener.updateObjects();
    }
}
