package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.GDX.AmmunitionGDX;
import ru.mipt.bit.platformer.objects.interfaces.Drawable;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.ObjectGDXAbt;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.Collection;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class Ammunition extends InvisibleAmmunition implements Drawable {

    private final ObjectGDXAbt ammunitionGDX;
    protected final TileMovement tileMovement;

    public Ammunition(
            Texture texture,
            GridPoint2 coordinates,
            float movementSpeed,
            float movementProgress,
            Direction direction,
            TileMovement tileMovement,
            int damage
    ) {
        super(coordinates, movementSpeed, movementProgress, direction, damage);
        this.ammunitionGDX = new AmmunitionGDX(texture);
        this.tileMovement = tileMovement;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    @Override
    public ObjectGDXAbt getObjectGDX() {
        return ammunitionGDX;
    }

    @Override
    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, ammunitionGDX.getGraphics(), ammunitionGDX.getRectangle(), rotation);
    }

    @Override
    public void move(float deltaTime, Collection<? extends GameObjectAbt> obstacles, Level level) {
        tileMovement.moveRectangleBetweenTileCenters(ammunitionGDX.getRectangle(), coordinates, destinationCoordinates, movementProgress);
        super.move(deltaTime, obstacles, level);
    }

}
