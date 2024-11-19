package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.GDX.TankGDX;
import ru.mipt.bit.platformer.objects.interfaces.*;
import ru.mipt.bit.platformer.util.TileMovement;


import java.util.Collection;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank extends Ghost implements Drawable, Shootable {

    protected ObjectGDXAbt tankGDX;
    protected final TileMovement tileMovement;

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
    public ObjectGDXAbt getObjectGDX() {
        return tankGDX;
    }

    @Override
    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, tankGDX.getGraphics(), tankGDX.getRectangle(), rotation);
    }

    @Override
    public void move(float deltaTime, Collection<? extends GameObjectAbt> obstacles, Level level) {
        tileMovement.moveRectangleBetweenTileCenters(tankGDX.getRectangle(), coordinates, destinationCoordinates, movementProgress);
        super.move(deltaTime, obstacles, level);
    }

    @Override
    public Ammunition shoot() {
        Direction directionToShoot;
               if(rotation == 90f){
            directionToShoot = Direction.UP;
        } else if(rotation == -90f){
            directionToShoot = Direction.DOWN;
        } else if(rotation == -180f){
            directionToShoot = Direction.LEFT;
        } else {
            directionToShoot = Direction.RIGHT;
        }
        return new Ammunition(new Texture("/home/artem/Repositories/tank-software-design/src/main/resources/images/3uzvaykb5cro_64.png"),
                coordinates.cpy().add(directionToShoot.getGridPoint()),
                0.5f,
                0f,
                directionToShoot,
                tileMovement,
                20);
    }
}
