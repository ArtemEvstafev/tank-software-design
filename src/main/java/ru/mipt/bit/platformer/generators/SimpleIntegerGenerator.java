package ru.mipt.bit.platformer.generators;

import static com.badlogic.gdx.math.MathUtils.random;
import static java.lang.Math.abs;

import java.util.Random;

public class SimpleIntegerGenerator implements IntegerGenerator {

    private Random random;

    public SimpleIntegerGenerator() {
        random = new Random();
    }

    public SimpleIntegerGenerator(int seed) {
        random = new Random(seed);
    }

    @Override
    public int generate(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}
