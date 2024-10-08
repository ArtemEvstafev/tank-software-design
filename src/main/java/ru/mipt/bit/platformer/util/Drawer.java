package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.objects.Drawable;

import java.util.Collection;

public class Drawer {

    public static void draw(Batch batch, final Collection<Drawable> drawables) {
        for (final Drawable drawable : drawables) {
            drawable.draw(batch);
        }
    }

    public static void dispose(final Collection<Drawable> drawables){
        for (final Drawable drawable : drawables) {
            drawable.dispose();
        }
    }
}
