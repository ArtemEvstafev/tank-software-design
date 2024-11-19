package ru.mipt.bit.platformer.objects.physical;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.generators.IntegerGenerator;
import ru.mipt.bit.platformer.generators.SimpleIntegerGenerator;
import ru.mipt.bit.platformer.keys.Direction;
import ru.mipt.bit.platformer.levels.Level;
import ru.mipt.bit.platformer.objects.GDX.TankAIGDX;
import ru.mipt.bit.platformer.objects.interfaces.AI;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
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

    public TankAI(TankAI copyFrom) {
        this(
                copyFrom.getObjectGDX().getTexture(),
                copyFrom.getCoordinates(),
                copyFrom.getMovementSpeed(),
                copyFrom.getMovementProgress(),
                copyFrom.getRotation(),
                copyFrom.getTileMovement(),
                copyFrom.getIntegerGenerator());
    }

    public static TankAI getDefaultTankAIFromCoordinatesAndTMTLayer(GridPoint2 coordinate, TiledMapTileLayer TMTLayer) {
        return new TankAI
                (
                        new Texture("src/main/resources/images/tank_blue.png"),
                        coordinate,
                        0.4f,
                        1f,
                        0,
                        new TileMovement(TMTLayer, Interpolation.smooth),
                        new SimpleIntegerGenerator()
                );
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
        setDirection(generateDirection());
        super.changeMovementState(obstacles, level);
    }
}
