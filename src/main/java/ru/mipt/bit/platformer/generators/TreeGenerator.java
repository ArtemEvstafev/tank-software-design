package ru.mipt.bit.platformer.generators;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.Tree;

import java.util.*;

public class TreeGenerator implements ObjectGenerator<Tree> {
    final private CoordinatesGenerator coordinatesGenerator;
    final private IntegerGenerator integerGenerator;
    final private List<String> textures;
    final private TiledMapTileLayer groundLayer;

    public TreeGenerator(CoordinatesGenerator coordinatesGenerator, IntegerGenerator integerGenerator, List<String> textures, TiledMapTileLayer groundLayer) {
        this.coordinatesGenerator = coordinatesGenerator;
        this.integerGenerator = integerGenerator;
        this.textures = textures;
        this.groundLayer = groundLayer;
    }

    public TreeGenerator(CoordinatesGenerator coordinatesGenerator, List<String> textures, TiledMapTileLayer groundLayer) {
        this.coordinatesGenerator = coordinatesGenerator;
        this.integerGenerator = coordinatesGenerator.getIntegerGenerator();
        this.textures = textures;
        this.groundLayer = groundLayer;
    }

    @Override
    public void generate(int n, Collection<? super Tree> destination) {
        List<GridPoint2> positions = new ArrayList<>();
        coordinatesGenerator.generate(n, positions);
        positions.stream().forEach(position -> {
            destination.add(new Tree(
                    new Texture(textures.get(integerGenerator.generate(0, textures.size() - 1))),
                    position,
                    groundLayer
            ));
        });
    }
}
