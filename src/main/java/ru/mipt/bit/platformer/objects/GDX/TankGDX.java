package ru.mipt.bit.platformer.objects.GDX;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.objects.interfaces.ObjectGDXAbt;

public class TankGDX extends ObjectGDXAbt {

    private static final Character drawableCharacter = 'X';

    public TankGDX(Texture texture) {
        super(texture);
    }

    public static Character getDrawableCharacterStatic() {
        return drawableCharacter;
    }

    @Override
    public Character getDrawableCharacter() {
        return drawableCharacter;
    }
}
