package ru.mipt.bit.platformer.util.gameLoaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.generators.*;
import ru.mipt.bit.platformer.levels.DrawableLevel;
import ru.mipt.bit.platformer.levels.EmptyDrawableLevel;
import ru.mipt.bit.platformer.objects.interfaces.Destroyable;
import ru.mipt.bit.platformer.objects.interfaces.Drawable;
import ru.mipt.bit.platformer.objects.interfaces.GameObjectAbt;
import ru.mipt.bit.platformer.objects.physical.*;
import ru.mipt.bit.platformer.util.Mover;
import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.util.files.FileSaver;
import ru.mipt.bit.platformer.util.files.TxtSaver;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class RandomGeneratedGameLoader implements GameLoader {

    private final Batch batch;
    private final DrawableLevel level;

    private final Collection<GameObjectAbt> allObjects = new HashSet<>();

    public RandomGeneratedGameLoader() {
        batch = new SpriteBatch();

        level = new EmptyDrawableLevel(new TmxMapLoader().load("level.tmx"), batch);

        Mover mover = new Mover(new TileMovement(level.getGroundLayer(), Interpolation.smooth));

        SimpleIntegerGenerator simpleIntegerGenerator = new SimpleIntegerGenerator();
        final CoordinatesGenerator coordinatesGenerator = new CoordinatesGenerator
                (
                        simpleIntegerGenerator,
                        level.getHeight(),
                        level.getWidth()
                );

        final ObjectGenerator<DestroyableTankAI> tankAIGenerator = new ShowHealthBarObjectGeneratorDecorator<>(new DestroyableTankAIGenerator(
                coordinatesGenerator,
                List.of(new Texture("images/tank_blue.png")),
                List.of(0.4f),
                1f,
                List.of(0f),
                mover.getTileMovement(),
                simpleIntegerGenerator,
                List.of(10, 50, 90)
        ));

        final ObjectGenerator<Tree> treeGenerator = new TreeGenerator(
                coordinatesGenerator,
                List.of("images/greenTree.png"),
                level.getGroundLayer()
        );

        final Tank tankPlayer = new Tank
                (
                        new Texture("images/tank_blue.png"),
                        coordinatesGenerator.generate(),
                        0.4f,
                        1f,
                        0,
                        mover.getTileMovement()
                );

        final GameObjectAbt tankAI =
                new ShowHealthBarDecorator<>(new DestroyableTankAI
                (
                        new Texture("images/tank_blue.png"),
                        coordinatesGenerator.generate(),
                        0.4f,
                        1f,
                        0,
                        mover.getTileMovement(),
                        simpleIntegerGenerator,
                        60
                ));

        allObjects.add(tankPlayer);
        allObjects.add(tankAI);

        tankAIGenerator.generate( 2, allObjects);
          treeGenerator.generate(10, allObjects);

        Collection<Drawable> drawables = new HashSet<>();
        for (GameObjectAbt gameObject : allObjects) {
            if (gameObject instanceof Drawable) {
                drawables.add((Drawable) gameObject);
            }
        }

        final FileSaver fileSaver = new TxtSaver(level, drawables);
        fileSaver.saveToFile("src/main/res/level.txt");
    }

    @Override
    public Batch getBatch() {
        return batch;
    }

    @Override
    public DrawableLevel getLevel() {
        return level;
    }

    @Override
    public Collection<GameObjectAbt> getObjects() {
        return allObjects;
    }
}
