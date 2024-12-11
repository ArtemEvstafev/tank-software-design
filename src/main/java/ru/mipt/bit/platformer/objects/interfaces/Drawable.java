package ru.mipt.bit.platformer.objects.interfaces;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface Drawable extends GameObject {

    ObjectGDXAbt getObjectGDX();

    void draw(Batch batch);

}
