package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.generators.IntegerGenerator;
import ru.mipt.bit.platformer.objects.interfaces.Destroyable;
import ru.mipt.bit.platformer.util.TileMovement;

public class DestroyableTankAI extends TankAI implements Destroyable {
    private int health;

    public DestroyableTankAI(Texture texture,
                             GridPoint2 coordinates,
                             float movementSpeed,
                             float movementProgress,
                             float rotation,
                             TileMovement tileMovement,
                             IntegerGenerator integerGenerator,
                             int health) {
        super(texture, coordinates, movementSpeed, movementProgress, rotation, tileMovement, integerGenerator);
        this.health = health;
    }

    public DestroyableTankAI(TankAI tankAI,
                             int health) {
        this(
                tankAI.getObjectGDX().getTexture(),
                tankAI.getCoordinates(),
                tankAI.getMovementSpeed(),
                tankAI.getMovementProgress(),
                tankAI.getRotation(),
                tankAI.getTileMovement(),
                tankAI.getIntegerGenerator(),
                health
        );
    }

    public DestroyableTankAI(TankAI tankAI) {
        this(tankAI, 100);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getHealth() {
        return this.health;
    }


    @Override
    public void getDamage(int damage) {
        this.health = this.health - damage;
    }

    @Override
    public void destroy() {
        setHealth(0);
    }
}
