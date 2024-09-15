package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public interface Drawable {
    Texture getTexture();
    TextureRegion getGraphics();
    Rectangle getRectangle();
    void dispose();
    default float getRotation(){
        return 0.f;
    }
}
