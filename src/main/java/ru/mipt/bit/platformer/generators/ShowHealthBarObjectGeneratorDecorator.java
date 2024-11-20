package ru.mipt.bit.platformer.generators;

import ru.mipt.bit.platformer.objects.interfaces.*;
import ru.mipt.bit.platformer.objects.physical.ShowHealthBarDecorator;

public class ShowHealthBarObjectGeneratorDecorator<T extends Drawable & Destroyable & Movable & Shootable & AI> extends ObjectGenerator<T> {

    ObjectGenerator<T> wrapped;

    public ShowHealthBarObjectGeneratorDecorator(ObjectGenerator<T> wrapped) {
        super(wrapped.coordinatesGenerator, wrapped.integerGenerator);
        this.wrapped = wrapped;
    }

    @Override
    T generate() {
        return (T) new ShowHealthBarDecorator<>(wrapped.generate());
    }
}
