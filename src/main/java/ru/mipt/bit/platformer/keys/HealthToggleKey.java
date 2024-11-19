package ru.mipt.bit.platformer.keys;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.util.HealthBarSetting;

import java.util.Arrays;

public class HealthToggleKey implements Key {

    private final int[] keys;

    public HealthToggleKey(int[] keys) {
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
