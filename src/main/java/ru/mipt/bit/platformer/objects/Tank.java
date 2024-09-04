package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;


import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank {

    private final float MOVEMENT_SPEED;
    // Texture decodes an image file and loads it into GPU memory, it represents a native resource
    private final Texture blueTankTexture;
    // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
    private final TextureRegion playerGraphics;
    private final Rectangle playerRectangle;
    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    private final GridPoint2 playerCoordinates;
    // which tile the player want to go next
    private final GridPoint2 playerDestinationCoordinates;
    private float playerMovementProgress;
    private float playerRotation;

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
        this.blueTankTexture = blueTankTexture;
        this.playerGraphics = new TextureRegion(blueTankTexture);
        this.playerRectangle = createBoundingRectangle(playerGraphics);
        this.playerDestinationCoordinates = playerDestinationCoordinates;
        this.playerCoordinates = new GridPoint2(playerDestinationCoordinates);
        this.playerMovementProgress = playerMovementProgress;
        this.playerRotation = playerRotation;

    }

    public float getMOVEMENT_SPEED() {
        return MOVEMENT_SPEED;
    }

    public Rectangle getPlayerRectangle() {
        return playerRectangle;
    }

    public TextureRegion getPlayerGraphics() {
        return playerGraphics;
    }

    public GridPoint2 getPlayerCoordinates() {
        return playerCoordinates;
    }

    public void setPlayerCoordinates(GridPoint2 playerCoordinates) {
        this.playerCoordinates.set(playerCoordinates);
    }

    public GridPoint2 getPlayerDestinationCoordinates() {
        return playerDestinationCoordinates;
    }

    public void disposeTexture() {
        blueTankTexture.dispose();
    }

    public float getPlayerMovementProgress() {
        return playerMovementProgress;
    }

    public void setPlayerMovementProgress(float playerMovementProgress) {
        this.playerMovementProgress = playerMovementProgress;
    }

    public float getPlayerRotation() {
        return playerRotation;
    }

    public void setPlayerRotation(float playerRotation) {
        this.playerRotation = playerRotation;
    }

    public void addPlayerDestinationCoordinates(int number, boolean toX) {

        if (toX) {
            playerDestinationCoordinates.x += number;
        }
        else {
            playerDestinationCoordinates.y += number;
        }
    }
}
