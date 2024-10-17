package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.TileMovement;


import java.util.Objects;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank implements Drawable, Movable {

    private final float movementSpeed;
    // Texture decodes an image file and loads it into GPU memory, it represents a native resource
    private final Texture texture;
    // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
    private final TextureRegion graphics;
    private final Rectangle rectangle;
    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    private final GridPoint2 coordinates;
    // which tile the player want to go next
    private final GridPoint2 destinationCoordinates;
    private float movementProgress;
    private float rotation;
    private final TileMovement tileMovement;

    public Tank
            (
                    Texture texture,
                    GridPoint2 coordinates,
                    float movementSpeed,
                    float movementProgress,
                    float rotation,
                    TileMovement tileMovement
            )
    {
        this.movementSpeed = movementSpeed;
        this.texture = texture;
        this.graphics = new TextureRegion(texture);
        this.tileMovement = tileMovement;
        this.rectangle = createBoundingRectangle(graphics);
        this.coordinates = coordinates;
        this.destinationCoordinates = new GridPoint2(coordinates);
        this.movementProgress = movementProgress;
        this.rotation = rotation;
    }

    @Override
    public float getMovementSpeed() {
        return movementSpeed;
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
    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    @Override
    public void setCoordinates(GridPoint2 coordinates) {
        this.coordinates.set(coordinates);
    }

    @Override
    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    @Override
    public float getMovementProgress() {
        return movementProgress;
    }

    @Override
    public void setMovementProgress(float movementProgress) {
        this.movementProgress = movementProgress;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public void changeDestinationCoordinates(GridPoint2 direction) {
        destinationCoordinates.x += direction.x;
        destinationCoordinates.y += direction.y;
    }

    @Override
    public void move(float deltaTime) {
        moveRectangle(tileMovement);
        setMovementProgress(continueProgress(movementProgress, deltaTime, movementSpeed));
        if (isEqual(movementProgress, 1f)) {
            setCoordinates(destinationCoordinates);
        }
    }

    private void moveRectangle(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(rectangle, coordinates, destinationCoordinates, movementProgress);
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
