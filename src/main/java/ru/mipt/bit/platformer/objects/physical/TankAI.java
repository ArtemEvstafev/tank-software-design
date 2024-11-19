package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.generators.IntegerGenerator;
import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.GDX.TankAIGDX;
import ru.mipt.bit.platformer.objects.interfaces.AI;
import ru.mipt.bit.platformer.objects.interfaces.Drawable;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.interfaces.Movable;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.Collection;

public class TankAI extends Tank implements AI {
    private final IntegerGenerator integerGenerator;

    public TankAI(Texture texture,
                  GridPoint2 coordinates,
                  float movementSpeed,
                  float movementProgress,
                  float rotation,
                  TileMovement tileMovement,
                  IntegerGenerator integerGenerator) {
        super(texture, coordinates, movementSpeed, movementProgress, rotation, tileMovement);
        this.tankGDX = new TankAIGDX(texture);
        this.integerGenerator = integerGenerator;
    }

    public TankAI(TankAI wrapped) {
        this(
                wrapped.getObjectGDX().getTexture(),
                wrapped.getCoordinates(),
                wrapped.getMovementSpeed(),
                wrapped.getMovementProgress(),
                wrapped.getRotation(),
                wrapped.getTileMovement(),
                wrapped.getIntegerGenerator());
    }

    public IntegerGenerator getIntegerGenerator() {
        return integerGenerator;
    }

    @Override
    public Direction generateDirection() {
        return Direction.values()[integerGenerator.generate(0, 3)];
    }

    @Override
    public void changeMovementState(Collection<? extends GameObjectAbt> obstacles, Level level) {
//        setDirection(generateDirection());
        super.changeMovementState(obstacles, level);
    }
}
