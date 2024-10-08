package ru.mipt.bit.platformer.generators;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoordinatesGenerator implements ObjectGenerator<GridPoint2> {

    private final IntegerGenerator integerGenerator;
    private final int height, width;

    public CoordinatesGenerator(IntegerGenerator integerGenerator, int height, int width) {
        this.integerGenerator = integerGenerator;
        this.height = height;
        this.width = width;
    }

    @Override
    public void generate(int n, Collection<? super GridPoint2> destination) {
        n = Math.min(n, height * width / 2);
        Set<GridPoint2> objects = new HashSet<>();
        while (objects.size() < n) {
            objects.add(new GridPoint2(integerGenerator.generate(0, width - 1), integerGenerator.generate(0, height - 1)));
        }
        destination.addAll(objects);
    }

    public IntegerGenerator getIntegerGenerator() {
        return integerGenerator;
    }
}
