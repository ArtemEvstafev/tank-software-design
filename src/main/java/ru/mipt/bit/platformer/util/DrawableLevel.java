package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class DrawableLevel {
    final private TiledMap level;
    final private MapRenderer levelRenderer;
    final private TiledMapTileLayer groundLayer;

    public DrawableLevel(TiledMap level, Batch batch) {
        this.level = level;
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        groundLayer = getSingleLayer(level);
    }

    public MapRenderer getLevelRenderer() {
        return levelRenderer;
    }

    public TiledMap getLevel() {
        return level;
    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

    public void dispose() {
        level.dispose();
    }

    public void render() {
        levelRenderer.render();
    }
}
