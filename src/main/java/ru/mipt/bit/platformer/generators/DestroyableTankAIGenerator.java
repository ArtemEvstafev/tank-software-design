package ru.mipt.bit.platformer.generators;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.objects.physical.DestroyableTankAI;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.List;

public class DestroyableTankAIGenerator extends ObjectGenerator<DestroyableTankAI> {
    private final List<Integer> healths;
    private final List<Texture> textures;
    private final List<Float> movementSpeeds;
    private final float movementProgress;
    private final List<Float> rotations;
    private final TileMovement tileMovement;

    public DestroyableTankAIGenerator(CoordinatesGenerator coordinatesGenerator,
                                      List<Texture> textures,
                                      List<Float> movementSpeeds,
                                      float movementProgress,
                                      List<Float> rotations,
                                      TileMovement tileMovement,
                                      IntegerGenerator integerGenerator,
                                      List<Integer> healths) {
        super(coordinatesGenerator, integerGenerator);
        this.textures = textures;
        this.movementSpeeds = movementSpeeds;
        this.movementProgress = movementProgress;
        this.rotations = rotations;
        this.tileMovement = tileMovement;
        this.healths = healths;
    }

    public DestroyableTankAIGenerator(CoordinatesGenerator coordinatesGenerator,
                                      List<Texture> textures,
                                      List<Float> movementSpeeds,
                                      float movementProgress,
                                      List<Float> rotations,
                                      TileMovement tileMovement) {

        this(coordinatesGenerator,
                textures,
                movementSpeeds,
                movementProgress,
                rotations,
                tileMovement,
                coordinatesGenerator.getIntegerGenerator(),
                List.of(100));
    }

    public DestroyableTankAIGenerator(CoordinatesGenerator coordinatesGenerator,
                                      List<Texture> textures,
                                      List<Float> movementSpeeds,
                                      float movementProgress,
                                      List<Float> rotations,
                                      TileMovement tileMovement,
                                      List<Integer> healths) {

        this(coordinatesGenerator,
                textures,
                movementSpeeds,
                movementProgress,
                rotations,
                tileMovement,
                coordinatesGenerator.getIntegerGenerator(),
                healths);
    }

    @Override
    public DestroyableTankAI generate() {
        return new DestroyableTankAI
                (
                        textures.get(generateIndex(textures.size())),
                        coordinatesGenerator.generate(),
                        movementSpeeds.get(generateIndex(movementSpeeds.size())),
                        movementProgress,
                        rotations.get(generateIndex(rotations.size())),
                        tileMovement,
                        integerGenerator,
                        healths.get(generateIndex(healths.size()))
                );
    }
}
