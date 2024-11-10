package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Shootable;
import ru.mipt.bit.platformer.objects.physical.InvisibleAmmunition;

import java.util.Arrays;
import java.util.Collection;

public class ShootKey implements Key{

    private final int[] keys;
    private final Collection<? extends Shootable> shootables;
    Collection<GameObjectAbt> newObjects;

    public ShootKey(int[] keys, Collection<? extends Shootable> shootables, Collection<GameObjectAbt> newObjects) {
        this.keys = keys;
        this.shootables = shootables;
        this.newObjects = newObjects;
    }

    @Override
    public boolean isPressed() {
        return Arrays.stream(keys).anyMatch(key -> Gdx.input.isKeyJustPressed(key));
    }

    @Override
    public void doAction() {
        for (Shootable shootable : shootables) {
            newObjects.add(shootable.shoot());
        }
    }
}
