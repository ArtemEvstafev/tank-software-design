package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.objects.interfaces.Movable;
import ru.mipt.bit.platformer.objects.interfaces.Shootable;

import java.util.Arrays;
import java.util.Collection;

public class ShootKey implements Key{

    private final int[] keys;
    private final Collection<? extends Shootable> shootables;

    public ShootKey(int[] keys, Collection<? extends Shootable> shootables) {
        this.keys = keys;
        this.shootables = shootables;
    }

    @Override
    public boolean isPressed() {
        return Arrays.stream(keys).anyMatch(key -> Gdx.input.isKeyJustPressed(key));
    }

    @Override
    public void doAction() {
        for (Shootable shootable : shootables) {
            shootable.shoot();
        }
    }
}
