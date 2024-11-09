package ru.mipt.bit.platformer.objects.GDX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.objects.interfaces.ObjectGDX;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class TreeGDX implements ObjectGDX {

    private Texture texture;
    private TextureRegion graphics;
    private Rectangle rectangle;

    public TreeGDX(Texture greenTreeTexture) {
        this.texture = greenTreeTexture;
        this.graphics = new TextureRegion(greenTreeTexture);
        this.rectangle = createBoundingRectangle(graphics);
    }


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

    }

    @Override
    public void draw(Batch batch) {

    }
}
