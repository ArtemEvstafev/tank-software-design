package ru.mipt.bit.platformer.objects.interfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public abstract class ObjectGDXAbt implements ObjectGDX {
    // Texture decodes an image file and loads it into GPU memory, it represents a native resource
    protected Texture texture;
    // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
    protected TextureRegion graphics;
    protected Rectangle rectangle;

    protected ObjectGDXAbt(Texture texture) {
        this.texture = texture;
        this.graphics = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(graphics);
    }

    public abstract Character getDrawableCharacter();

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public TextureRegion getGraphics() {
        return graphics;
    }

    @Override
    public void setGraphics(TextureRegion graphics) {
        this.graphics = graphics;
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
