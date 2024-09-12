package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;


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

    public Tank
            (
                    Texture texture,
                    GridPoint2 destinationCoordinates,
                    float speed,
                    float movementProgress,
                    float rotation
            )
    {
        this.movementSpeed = speed;
        this.texture = texture;
        this.graphics = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(graphics);
        this.destinationCoordinates = destinationCoordinates;
        this.coordinates = new GridPoint2(destinationCoordinates);
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

    public void changeDestinationCoordinates(int number, boolean toX) {

        if (toX) {
            destinationCoordinates.x += number;
        }
        else {
            destinationCoordinates.y += number;
        }
    }
}
