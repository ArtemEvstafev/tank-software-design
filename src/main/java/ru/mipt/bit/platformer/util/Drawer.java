package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.objects.interfaces.Drawable;
import ru.mipt.bit.platformer.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;

import java.util.Collection;

public class Drawer {

    public static void draw(Batch batch, final Collection<? extends GameObjectAbt> allObjects) {
        for (GameObjectAbt objectAbt : allObjects) {
            if (objectAbt instanceof Drawable drawable) {
                drawable.draw(batch);
            }
        }
    }

    public static void dispose(final Collection<? extends GameObjectAbt> allObjects){
        for (GameObjectAbt objectAbt : allObjects) {
            if (objectAbt instanceof Drawable drawable) {
                drawable.getObjectGDX().dispose();
            }
        }
    }
}
