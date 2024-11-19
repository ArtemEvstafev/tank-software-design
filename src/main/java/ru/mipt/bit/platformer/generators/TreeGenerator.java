package ru.mipt.bit.platformer.generators;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.objects.physical.Tree;

import java.util.*;

public class TreeGenerator extends ObjectGenerator<Tree> {
    final private List<String> textures;
    final private TiledMapTileLayer groundLayer;

    public TreeGenerator(CoordinatesGenerator coordinatesGenerator, IntegerGenerator integerGenerator, List<String> textures, TiledMapTileLayer groundLayer) {
        super(coordinatesGenerator, integerGenerator);
        this.textures = textures;
        this.groundLayer = groundLayer;
    }

    public TreeGenerator(CoordinatesGenerator coordinatesGenerator, List<String> textures, TiledMapTileLayer groundLayer) {
        this(coordinatesGenerator, coordinatesGenerator.getIntegerGenerator(), textures, groundLayer);
    }

    @Override
    public Tree generate() {
        return new Tree(new Texture(
                textures.get(integerGenerator.generate(0, textures.size() - 1))),
                coordinatesGenerator.generate(),
                groundLayer
        );
    }
}
