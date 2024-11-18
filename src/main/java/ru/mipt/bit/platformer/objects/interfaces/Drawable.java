package ru.mipt.bit.platformer.objects.interfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

public interface Drawable extends GameObject {

    ObjectGDXAbt getObjectGDX();

    void draw(Batch batch);

}
