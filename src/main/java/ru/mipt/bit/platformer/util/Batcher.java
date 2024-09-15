package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.objects.Drawable;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class Batcher {
    private final Batch batch;

    public Batcher(Batch batch) {
        this.batch = batch;
    }

    public Batch getBatch() {
        return batch;
    }

    public void draw(final Drawable ...drawables) {
        // start recording all drawing commands
        batch.begin();

        for (final Drawable drawable : drawables) {
            drawTextureRegionUnscaled(batch, drawable.getGraphics(), drawable.getRectangle(), drawable.getRotation());
        }

        // submit all drawing requests
        batch.end();
    }

    public void dispose() {
        batch.dispose();
    }
}
