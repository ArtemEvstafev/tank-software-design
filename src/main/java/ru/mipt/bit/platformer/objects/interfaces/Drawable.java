package ru.mipt.bit.platformer.objects.interfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

public interface Drawable extends GameObject {
    Texture getTexture();

    void setTexture(Texture texture);

    TextureRegion getGraphics();

    void setGraphics(TextureRegion graphics);

    Rectangle getRectangle();

    void setRectangle(Rectangle rectangle);

    Character getDrawableCharacter();

    void dispose();

    void draw(Batch batch);

}
