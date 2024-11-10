package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.objects.GDX.TreeGDX;
import ru.mipt.bit.platformer.objects.interfaces.Drawable;
import ru.mipt.bit.platformer.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tree extends GameObjectAbt implements Drawable {

    private final TreeGDX treeGDX;
    private static final Character drawableCharacter = 'T';

    public Tree
            (
                    Texture greenTreeTexture,
                    GridPoint2 coordinates,
                    TiledMapTileLayer groundLayer
            ) {
        super(coordinates, 0f);
        this.treeGDX = new TreeGDX(greenTreeTexture);
        this.placeOnLayer(groundLayer);
    }

    public void placeOnLayer(TiledMapTileLayer groundLayer) {
        moveRectangleAtTileCenter(groundLayer, treeGDX.getRectangle(), coordinates);
    }

    @Override
    public Texture getTexture() {
        return treeGDX.getTexture();
    }

    @Override
    public void setTexture(Texture texture) {
        treeGDX.setTexture(texture);
    }

    @Override
    public TextureRegion getGraphics() {
        return treeGDX.getGraphics();
    }

    @Override
    public void setGraphics(TextureRegion graphics) {
        treeGDX.setGraphics(graphics);
    }

    @Override
    public Rectangle getRectangle() {
        return treeGDX.getRectangle();
    }

    @Override
    public void setRectangle(Rectangle rectangle) {
        treeGDX.setRectangle(rectangle);
    }

    @Override
    public Character getDrawableCharacter() {
        return drawableCharacter;
    }

    public static Character getDrawableCharacterStatic() {
        return drawableCharacter;
    }

    @Override
    public void dispose() {
        treeGDX.dispose();
    }

    @Override
    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, treeGDX.getGraphics(), treeGDX.getRectangle(), rotation);
    }
}
