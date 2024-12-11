package ru.mipt.bit.platformer.objects.interfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public interface ObjectGDX {

    Texture getTexture();

    void setTexture(Texture texture);

    TextureRegion getGraphics();

    void setGraphics(TextureRegion graphics);

    Rectangle getRectangle();

    void setRectangle(Rectangle rectangle);

    void dispose();
}
