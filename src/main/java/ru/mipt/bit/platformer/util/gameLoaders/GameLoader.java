package ru.mipt.bit.platformer.util.gameLoaders;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.levels.DrawableLevel;
import ru.mipt.bit.platformer.objects.interfaces.Drawable;
import ru.mipt.bit.platformer.objects.interfaces.Movable;

import java.util.Collection;

public interface GameLoader {
    Batch getBatch();

    DrawableLevel getLevel();

    Collection<Drawable> getDrawables();

    Collection<Movable> getMovables();
}
