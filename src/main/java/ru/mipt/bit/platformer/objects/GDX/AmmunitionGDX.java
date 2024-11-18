package ru.mipt.bit.platformer.objects.GDX;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.objects.interfaces.ObjectGDX;
import ru.mipt.bit.platformer.objects.interfaces.ObjectGDXAbt;

public class AmmunitionGDX extends ObjectGDXAbt {

    private static final Character drawableCharacter = '.';

    public AmmunitionGDX(Texture texture) {
        super(texture);
    }

    @Override
    public Character getDrawableCharacter() {
        return drawableCharacter;
    }

    public static Character getDrawableCharacterStatic() {
        return drawableCharacter;
    }
}
