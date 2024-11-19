package ru.mipt.bit.platformer.generators;

import java.util.Collection;

import static java.lang.Math.min;

public abstract class ObjectGenerator<T> {

    protected final CoordinatesGenerator coordinatesGenerator;
    protected final IntegerGenerator integerGenerator;

    ObjectGenerator(CoordinatesGenerator coordinatesGenerator, IntegerGenerator integerGenerator) {
        this.coordinatesGenerator = coordinatesGenerator;
        this.integerGenerator = integerGenerator;
    }

    public Collection<? super T> generate(int n, Collection<? super T> destination) {
        final int size = destination.size();
        while (destination.size() < min(n + size, coordinatesGenerator.getHeight() * coordinatesGenerator.getWidth())) {
            var generate = generate();
            if (!destination.contains(generate)) {
                destination.add(generate);
            }
        }
        return destination;
    }

    abstract T generate();

    int generateIndex(int size) {
        return integerGenerator.generate(0, size - 1);
    }
}
