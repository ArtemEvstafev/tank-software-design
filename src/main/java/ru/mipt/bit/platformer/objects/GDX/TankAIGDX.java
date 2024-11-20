package ru.mipt.bit.platformer.objects.GDX;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.objects.interfaces.ObjectGDXAbt;

public class TankAIGDX extends ObjectGDXAbt {
    private static final Character drawableCharacter = 'A';

    public TankAIGDX(Texture texture) {
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
