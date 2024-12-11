package ru.mipt.bit.platformer.generators;

import java.util.Random;

public class SimpleIntegerGenerator implements IntegerGenerator {

    private final Random random;

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
