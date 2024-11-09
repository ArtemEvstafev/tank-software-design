package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.objects.interfaces.Drawable;
import ru.mipt.bit.platformer.util.HealthBarSetting;

import java.util.Arrays;
import java.util.Collection;

public class HealthToggleKey implements Key {

    protected final Collection<Drawable> drawables;
    private final int[] keys;

    public HealthToggleKey(Collection<Drawable> drawables, int[] keys) {
        this.drawables = drawables;
        this.keys = keys;
    }

    @Override
    public boolean isPressed() {
        return Arrays.stream(keys).anyMatch(key -> Gdx.input.isKeyJustPressed(key));
    }

    @Override
    public void doAction() {
        HealthBarSetting.showHealthBar = !HealthBarSetting.showHealthBar;
    }
}
