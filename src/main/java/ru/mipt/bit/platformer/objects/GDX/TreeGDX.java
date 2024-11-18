package ru.mipt.bit.platformer.objects.GDX;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.objects.interfaces.ObjectGDX;
import ru.mipt.bit.platformer.objects.interfaces.ObjectGDXAbt;

public class TreeGDX extends ObjectGDXAbt {

    private static final Character drawableCharacter = 'T';

    public TreeGDX(Texture greenTreeTexture) {
        super(greenTreeTexture);
    }

    public static Character getDrawableCharacterStatic() {
        return drawableCharacter;
    }

    @Override
    public Character getDrawableCharacter() {
        return drawableCharacter;
    }
}
