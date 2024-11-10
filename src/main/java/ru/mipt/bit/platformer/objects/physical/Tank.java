package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.objects.GDX.TankGDX;
import ru.mipt.bit.platformer.objects.interfaces.Drawable;
import ru.mipt.bit.platformer.util.TileMovement;


import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank extends Ghost implements Drawable {

    protected TankGDX tankGDX;
    protected final TileMovement tileMovement;
    private static final Character drawableCharacter = 'X';

    public Tank
            (
                    Texture texture,
                    GridPoint2 coordinates,
                    float movementSpeed,
                    float movementProgress,
                    float rotation,
                    TileMovement tileMovement
            ) {
        super(coordinates, rotation, movementSpeed, movementProgress);
        this.tankGDX = new TankGDX(texture);
        this.tileMovement = tileMovement;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    @Override
    public Texture getTexture() {
        return tankGDX.getTexture();
    }

    @Override
    public void setTexture(Texture texture) {
        tankGDX.setTexture(texture);
    }

    @Override
    public TextureRegion getGraphics() {
        return tankGDX.getGraphics();
    }

    @Override
    public void setGraphics(TextureRegion graphics) {
        tankGDX.setGraphics(graphics);
    }

    @Override
    public Rectangle getRectangle() {
        return tankGDX.getRectangle();
    }

    @Override
    public void setRectangle(Rectangle rectangle) {
        tankGDX.setRectangle(rectangle);
    }

    @Override
    public Character getDrawableCharacter() {
        return drawableCharacter;
    }

    @Override
    public void dispose() {
        tankGDX.dispose();
    }

    @Override
    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, tankGDX.getGraphics(), tankGDX.getRectangle(), rotation);
    }

    public static Character getDrawableCharacterStatic() {
        return drawableCharacter;
    }

    @Override
    public void move(float deltaTime) {
        tileMovement.moveRectangleBetweenTileCenters(tankGDX.getRectangle(), coordinates, destinationCoordinates, movementProgress);
        super.move(deltaTime);
    }
}
