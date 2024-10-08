package ru.mipt.bit.platformer.generators;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Collection;
import java.util.List;

public interface ObjectGenerator<T> {
    void generate(int n, Collection<? super T> destination);
}
