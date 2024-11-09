package ru.mipt.bit.platformer.util.gameLoaders;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import ru.mipt.bit.platformer.levels.DrawableLevel;
import ru.mipt.bit.platformer.levels.FromFileDrawableLevel;
import ru.mipt.bit.platformer.objects.interfaces.Drawable;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Movable;
import ru.mipt.bit.platformer.util.files.TxtParser;

import java.util.Collection;
import java.util.HashSet;

public class FromFileGameLoader implements GameLoader {

    private final Batch batch;
    private final DrawableLevel level;
    private final Collection<Drawable> drawables = new HashSet<>();
    private final Collection<Movable>   movables = new HashSet<>();


    public FromFileGameLoader() {
        batch = new SpriteBatch();

        Collection<GameObjectAbt> objects = new HashSet<>();
        level = new FromFileDrawableLevel
                (
                        new TmxMapLoader().load("level.tmx"),
                        batch,
                        new TxtParser(),
                        "src/main/res/level.txt",
                        objects
                );

        for (GameObjectAbt object : objects) {
            if (object instanceof Movable) {
                movables.add((Movable) object);
            }
            if (object instanceof Drawable) {
                drawables.add((Drawable) object);
            }
        }
    }

    @Override
    public Batch getBatch() {
        return batch;
    }

    @Override
    public DrawableLevel getLevel() {
        return level;
    }

    @Override
    public Collection<Drawable> getDrawables() {
        return drawables;
    }

    @Override
    public Collection<Movable> getMovables() {
        return movables;
    }
}
