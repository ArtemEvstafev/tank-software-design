package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;


import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank {

    private final float MOVEMENT_SPEED;
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
                    Texture blueTankTexture,
                    GridPoint2 playerDestinationCoordinates,
                    float speed,
                    float playerMovementProgress,
                    float playerRotation
            )
    {
        this.MOVEMENT_SPEED = speed;
        this.texture = blueTankTexture;
        this.graphics = new TextureRegion(blueTankTexture);
        this.rectangle = createBoundingRectangle(graphics);
        this.destinationCoordinates = playerDestinationCoordinates;
        this.coordinates = new GridPoint2(playerDestinationCoordinates);
        this.movementProgress = playerMovementProgress;
        this.rotation = playerRotation;

    }

    public float getMOVEMENT_SPEED() {
        return MOVEMENT_SPEED;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public TextureRegion getGraphics() {
        return graphics;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GridPoint2 coordinates) {
        this.coordinates.set(coordinates);
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public void disposeTexture() {
        texture.dispose();
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public void setMovementProgress(float movementProgress) {
        this.movementProgress = movementProgress;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void addPlayerDestinationCoordinates(int number, boolean toX) {

        if (toX) {
            destinationCoordinates.x += number;
        }
        else {
            destinationCoordinates.y += number;
        }
    }
}
