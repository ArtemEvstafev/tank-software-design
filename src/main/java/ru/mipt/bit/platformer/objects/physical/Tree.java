package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.GDX.TreeGDX;
import ru.mipt.bit.platformer.objects.interfaces.*;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tree extends GameObjectAbt implements Drawable {

    private final ObjectGDXAbt treeGDX;

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

    public static Tree getDefaultTreeFromCoordinatesAndTMTLayer(GridPoint2 coordinate, TiledMapTileLayer TMTLayer) {
        return new Tree
                (
                        new Texture("src/main/resources/images/greenTree.png"),
                        coordinate,
                        TMTLayer
                );
    }

    public void placeOnLayer(TiledMapTileLayer groundLayer) {
        moveRectangleAtTileCenter(groundLayer, treeGDX.getRectangle(), coordinates);
    }

    @Override
    public ObjectGDXAbt getObjectGDX() {
        return treeGDX;
    }

    @Override
    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, treeGDX.getGraphics(), treeGDX.getRectangle(), rotation);
    }
}
