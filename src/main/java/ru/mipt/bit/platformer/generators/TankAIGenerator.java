package ru.mipt.bit.platformer.generators;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.objects.physical.TankAI;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.Collection;
import java.util.List;

import static java.lang.Math.min;

public class TankAIGenerator extends ObjectGenerator<TankAI> {
    final protected List<Texture> textures;
    final protected List<Float> movementSpeeds;
    final protected float movementProgress;
    final protected List<Float> rotations;
    final protected TileMovement tileMovement;

    public TankAIGenerator(CoordinatesGenerator coordinatesGenerator,
                           List<Texture> textures,
                           List<Float> movementSpeeds,
                           float movementProgress,
                           List<Float> rotations,
                           TileMovement tileMovement,
                           IntegerGenerator integerGenerator) {
        super(coordinatesGenerator, integerGenerator);
        this.textures = textures;
        this.movementSpeeds = movementSpeeds;
        this.movementProgress = movementProgress;
        this.rotations = rotations;
        this.tileMovement = tileMovement;
    }

    public TankAIGenerator(CoordinatesGenerator coordinatesGenerator,
                           List<Texture> textures,
                           List<Float> movementSpeeds,
                           float movementProgress,
                           List<Float> rotations,
                           TileMovement tileMovement) {

        this(
                coordinatesGenerator,
                textures,
                movementSpeeds,
                movementProgress,
                rotations,
                tileMovement,
                coordinatesGenerator.getIntegerGenerator()
        );
    }

    public TankAIGenerator(CoordinatesGenerator coordinatesGenerator, TankAI tankAI) {
        this(
                coordinatesGenerator,
                List.of(tankAI.getObjectGDX().getTexture()),
                List.of(tankAI.getMovementSpeed()),
                tankAI.getMovementProgress(),
                List.of(tankAI.getRotation()),
                tankAI.getTileMovement(),
                tankAI.getIntegerGenerator());
    }

    @Override
    public TankAI generate() {
        return new TankAI
                (
                        textures.get(generateIndex(textures.size())),
                        coordinatesGenerator.generate(),
                        movementSpeeds.get(generateIndex(movementSpeeds.size())),
                        movementProgress,
                        rotations.get(generateIndex(rotations.size())),
                        tileMovement,
                        integerGenerator
                );
    }
}
