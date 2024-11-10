//package ru.mipt.bit.platformer.generators;
//
//import java.util.Collection;
//import java.util.List;
//
//public class MakeDestroyableObjectGenerator<T> implements ObjectGenerator<T> {
//
//    private final ObjectGenerator<T> wrapped;
//
//    public MakeDestroyableObjectGenerator(ObjectGenerator<T> wrapped) {
//        this.wrapped = wrapped;
//    }
//
//    @Override
//    public Collection<? super T> generate(int n, Collection<? super T> destination) {
//        return wrapped.generate(n, destination);
//    }
//
//    @Override
//    public T generate() {
//        return wrapped.generate();
//    }
//}
