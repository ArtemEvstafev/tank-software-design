package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.objects.Drawable;

import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class Drawer {

    public static void draw(Batch batch, final List<Drawable> drawables) {
        for (final Drawable drawable : drawables) {
            drawTextureRegionUnscaled(batch, drawable.getGraphics(), drawable.getRectangle(), drawable.getRotation());
        }
    }

    public static void dispose(final List<Drawable> drawables){
        for (final Drawable drawable : drawables) {
            drawable.dispose();
        }
    }
}
