package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class Tree {

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

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public TextureRegion getGraphics() {
        return graphics;
    }

    public void disposeTexture() {
        texture.dispose();
    }

}
