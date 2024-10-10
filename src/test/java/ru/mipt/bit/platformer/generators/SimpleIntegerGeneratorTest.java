package ru.mipt.bit.platformer.generators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleIntegerGeneratorTest {

    @Test
    void generate() {
        IntegerGenerator generator = new SimpleIntegerGenerator();
        for (int i = 0; i < 100000; i++) {
            int a = generator.generate(0, 10);
            assertTrue(a >= 0 && a <= 10);
        }
    }

    @Test
    void generate0() {
        IntegerGenerator generator = new SimpleIntegerGenerator();
        for (int i = 0; i < 100000; i++) {
            int a = generator.generate(0, 0);
            assertEquals(0, a);
        }
    }

}