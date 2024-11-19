package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.levels.DrawableLevel;
import ru.mipt.bit.platformer.objects.interfaces.Drawable;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;

import java.util.Collection;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class Drawer {

    public static void draw(Batch batch, final Collection<? extends GameObjectAbt> allObjects) {
        for (GameObjectAbt objectAbt : allObjects) {
            if (objectAbt instanceof Drawable drawable) {
                drawable.draw(batch);
            }
        }
    }

    public static void draw(DrawableLevel level) {
        level.draw();
    }

    public static void drawAll(DrawableLevel level, Batch batch, final Collection<? extends GameObjectAbt> allObjects) {
        // clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        Drawer.draw(level);
        batch.begin();
        Drawer.draw(batch, allObjects);
        batch.end();
    }

    public static void dispose(final Collection<? extends GameObjectAbt> allObjects){
        for (GameObjectAbt objectAbt : allObjects) {
            if (objectAbt instanceof Drawable drawable) {
                drawable.getObjectGDX().dispose();
            }
        }
    }

    public static void disposeAll(final Collection<? extends GameObjectAbt> allObjects, DrawableLevel level, Batch batch) {
        dispose(allObjects);
        level.dispose();
        batch.dispose();
    }
}
