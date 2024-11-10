package ru.mipt.bit.platformer.generators;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.objects.interfaces.Destroyable;
import ru.mipt.bit.platformer.objects.physical.DestroyableTankAI;
import ru.mipt.bit.platformer.objects.physical.TankAI;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.Collection;
import java.util.List;

import static java.lang.Math.min;

public class DestroyableTankAIGenerator extends TankAIGenerator {
    private final List<Integer> healths;

    public DestroyableTankAIGenerator(CoordinatesGenerator coordinatesGenerator,
                                      List<Texture> textures,
                                      List<Float> movementSpeeds,
                                      float movementProgress,
                                      List<Float> rotations,
                                      TileMovement tileMovement,
                                      IntegerGenerator integerGenerator,
                                      List<Integer> healths) {

        super(coordinatesGenerator, textures, movementSpeeds, movementProgress, rotations, tileMovement, integerGenerator);
        this.healths = healths;
    }

    public DestroyableTankAIGenerator(CoordinatesGenerator coordinatesGenerator,
                                      List<Texture> textures,
                                      List<Float> movementSpeeds,
                                      float movementProgress,
                                      List<Float> rotations,
                                      TileMovement tileMovement) {

        super(coordinatesGenerator, textures, movementSpeeds, movementProgress, rotations, tileMovement);
        this.healths = List.of(100);
    }

    public DestroyableTankAIGenerator(CoordinatesGenerator coordinatesGenerator,
                                      List<Texture> textures,
                                      List<Float> movementSpeeds,
                                      float movementProgress,
                                      List<Float> rotations,
                                      TileMovement tileMovement,
                                      List<Integer> healths) {

        super(coordinatesGenerator, textures, movementSpeeds, movementProgress, rotations, tileMovement);
        this.healths = healths;
    }

    public DestroyableTankAIGenerator(CoordinatesGenerator coordinatesGenerator,
                                      TankAI tankAI,
                                      List<Integer> healths) {

        super(coordinatesGenerator, tankAI);
        this.healths = healths;
    }

    public DestroyableTankAIGenerator(CoordinatesGenerator coordinatesGenerator,
                                      DestroyableTankAI destroyableTankAI) {

        super(coordinatesGenerator, destroyableTankAI);
        this.healths = List.of(destroyableTankAI.getHealth());
    }

    @Override
    public DestroyableTankAI generate() {
        return new DestroyableTankAI(super.generate(), healths.get(generateIndex(healths.size())));
    }
}
