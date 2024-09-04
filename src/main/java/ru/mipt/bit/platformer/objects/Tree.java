package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class Tree {

    private final Texture greenTreeTexture;
    private final TextureRegion treeObstacleGraphics;
    private final GridPoint2 treeObstacleCoordinates;
    private final Rectangle treeObstacleRectangle;

    public Tree
            (
                    Texture greenTreeTexture,
                    GridPoint2 treeObstacleCoordinates
            )
    {
        this.greenTreeTexture = greenTreeTexture;
        this.treeObstacleGraphics = new TextureRegion(greenTreeTexture);
        this.treeObstacleCoordinates = treeObstacleCoordinates;
        this.treeObstacleRectangle = createBoundingRectangle(treeObstacleGraphics);

    }

    public void moveTreeAtTileCenter(TiledMapTileLayer groundLayer) {
        moveRectangleAtTileCenter(groundLayer, treeObstacleRectangle, treeObstacleCoordinates);
    }

    public GridPoint2 getTreeObstacleCoordinates() {
        return treeObstacleCoordinates;
    }

    public Rectangle getTreeObstacleRectangle() {
        return treeObstacleRectangle;
    }

    public TextureRegion getTreeObstacleGraphics() {
        return treeObstacleGraphics;
    }

    public void disposeTexture() {
        greenTreeTexture.dispose();
    }

}
