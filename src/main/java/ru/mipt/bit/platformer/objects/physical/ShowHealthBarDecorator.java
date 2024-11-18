package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.objects.interfaces.*;
import ru.mipt.bit.platformer.util.GdxGameUtils;
import ru.mipt.bit.platformer.util.HealthBarSetting;

public class ShowHealthBarDecorator<T extends Drawable & Destroyable> extends GameObjectAbt implements Drawable, Destroyable {

    T wrapped;

    public ShowHealthBarDecorator(T wrapped) {
        super(wrapped.getCoordinates(), wrapped.getRotation());
        this.wrapped = wrapped;
    }

    @Override
    public void setHealth(int health) {
        wrapped.setHealth(health);
    }

    @Override
    public int getHealth() {
        return wrapped.getHealth();
    }

    @Override
    public void destroy() {
        wrapped.destroy();
    }

    @Override
    public ObjectGDXAbt getObjectGDX() {
        return wrapped.getObjectGDX();
    }

    @Override
    public void draw(Batch batch) {
        wrapped.draw(batch);
        if (HealthBarSetting.showHealthBar) {
            renderHealthBar(batch);
        }
    }

    private void renderHealthBar(Batch batch) {
        var healthbarTexture = getHealthBarTexture(wrapped.getHealth());
        var rectangle = createRectangle();
        GdxGameUtils.drawTextureRegionUnscaled(batch, healthbarTexture, rectangle, 0f);
    }

    private TextureRegion getHealthBarTexture(float relativeHealth) {
        var pixmap = new Pixmap(90, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(0, 0, 90, 20);
        pixmap.setColor(Color.GREEN);
        pixmap.fillRectangle(0, 0, (int) (90 * relativeHealth), 20);
        var texture = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegion(texture);
    }

    private Rectangle createRectangle() {
        var rectangle = new Rectangle(getObjectGDX().getRectangle());
        rectangle.y += 90;
        return rectangle;
    }
}
