package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.objects.GDX.AmmunitionGDX;
import ru.mipt.bit.platformer.objects.interfaces.Drawable;
import ru.mipt.bit.platformer.objects.interfaces.ObjectGDX;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class Ammunition extends InvisibleAmmunition implements Drawable {

    private final ObjectGDX ammunitionGDX;
    protected final TileMovement tileMovement;
    private static final Character drawableCharacter = '.';

    public Ammunition(
            Texture texture,
            GridPoint2 coordinates,
            float movementSpeed,
            float movementProgress,
            Direction direction,
            TileMovement tileMovement
    ) {
        super(coordinates, movementSpeed, movementProgress);
        this.rotation = direction.getRotation();
        this.ammunitionGDX = new AmmunitionGDX(texture);
        this.tileMovement = tileMovement;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    @Override
    public Texture getTexture() {
        return ammunitionGDX.getTexture();
    }

    @Override
    public void setTexture(Texture texture) {
        ammunitionGDX.setTexture(texture);
    }

    @Override
    public TextureRegion getGraphics() {
        return ammunitionGDX.getGraphics();
    }

    @Override
    public void setGraphics(TextureRegion graphics) {
        ammunitionGDX.setGraphics(graphics);
    }

    @Override
    public Rectangle getRectangle() {
        return ammunitionGDX.getRectangle();
    }

    @Override
    public void setRectangle(Rectangle rectangle) {
        ammunitionGDX.setRectangle(rectangle);
    }

    @Override
    public Character getDrawableCharacter() {
        return drawableCharacter;
    }

    @Override
    public void dispose() {
        ammunitionGDX.dispose();
    }

    @Override
    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, ammunitionGDX.getGraphics(), ammunitionGDX.getRectangle(), rotation);
    }

    public static Character getDrawableCharacterStatic() {
        return drawableCharacter;
    }

    @Override
    public void move(float deltaTime) {
        tileMovement.moveRectangleBetweenTileCenters(ammunitionGDX.getRectangle(), coordinates, destinationCoordinates, movementProgress);
        super.move(deltaTime);
    }
}
