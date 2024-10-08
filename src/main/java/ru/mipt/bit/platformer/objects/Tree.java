package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import java.util.Objects;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tree implements Drawable, GameObject {

    private final Texture texture;
    private final TextureRegion graphics;
    private final GridPoint2 coordinates;
    private final float rotation = 0f;
    private final Rectangle rectangle;

    public Tree
            (
                    Texture greenTreeTexture,
                    GridPoint2 treeObstacleCoordinates,
                    TiledMapTileLayer groundLayer
            )
    {
        this.texture = greenTreeTexture;
        this.graphics = new TextureRegion(greenTreeTexture);
        this.coordinates = treeObstacleCoordinates;
        this.rectangle = createBoundingRectangle(graphics);
        this.placeOnLayer(groundLayer);
    }

    public void placeOnLayer(TiledMapTileLayer groundLayer) {
        moveRectangleAtTileCenter(groundLayer, rectangle, coordinates);
    }

    @Override
    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    @Override
    public void setCoordinates(GridPoint2 coordinates) {
        this.coordinates.set(coordinates);
    }

    @Override
    public void setRotation(float rotation) {}

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public TextureRegion getGraphics() {
        return graphics;
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, graphics, rectangle, rotation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameObject)) return false;
        GameObject gobject = (GameObject) o;
        return Objects.equals(getCoordinates(), gobject.getCoordinates());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCoordinates());
    }
}
