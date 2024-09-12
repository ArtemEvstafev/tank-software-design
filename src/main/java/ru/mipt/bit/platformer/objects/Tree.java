package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class Tree implements Drawable, Object {

    private final Texture texture;
    private final TextureRegion graphics;
    private final GridPoint2 coordinates;
    private final Rectangle rectangle;

    public Tree
            (
                    Texture greenTreeTexture,
                    GridPoint2 treeObstacleCoordinates
            )
    {
        this.texture = greenTreeTexture;
        this.graphics = new TextureRegion(greenTreeTexture);
        this.coordinates = treeObstacleCoordinates;
        this.rectangle = createBoundingRectangle(graphics);
    }

    public void moveTreeAtTileCenter(TiledMapTileLayer groundLayer) {
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
    public void disposeTexture() {
        texture.dispose();
    }

}
