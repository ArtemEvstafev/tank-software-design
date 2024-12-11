package ru.mipt.bit.platformer.levels;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.springframework.stereotype.Component;

@Component
public interface DrawableLevel extends Level {
    void dispose();

    void draw();

    TiledMapTileLayer getGroundLayer();
}
