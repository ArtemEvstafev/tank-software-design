package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Shootable;
import ru.mipt.bit.platformer.util.ObjectsUpdateListener;

import java.util.Arrays;
import java.util.Collection;

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
        ObjectsUpdateListener.updateObjects(allObjects);
    }
}
