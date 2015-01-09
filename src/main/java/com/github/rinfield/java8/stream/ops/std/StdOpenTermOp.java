package com.github.rinfield.java8.stream.ops.std;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.github.rinfield.java8.stream.ops.StreamWrapper;

public interface StdOpenTermOp<T, S extends StreamWrapper<T, S>> extends
    StreamWrapper<T, S> {

    default void forEach(final Consumer<? super T> paramConsumer) {
        stream().forEach(paramConsumer);
    }

    default void forEachOrdered(final Consumer<? super T> paramConsumer) {
        stream().forEachOrdered(paramConsumer);
    }

    default Object[] toArray() {
        return stream().toArray();
    }

    default <A> A[] toArray(final IntFunction<A[]> paramIntFunction) {
        return stream().toArray(paramIntFunction);
    }

    default T reduce(final T paramT, final BinaryOperator<T> paramBinaryOperator) {
        return stream().reduce(paramT, paramBinaryOperator);
    }

    default Optional<T> reduce(final BinaryOperator<T> paramBinaryOperator) {
        return stream().reduce(paramBinaryOperator);
    }

    default <U> U reduce(final U paramU,
        final BiFunction<U, ? super T, U> paramBiFunction,
        final BinaryOperator<U> paramBinaryOperator) {
        return stream().reduce(paramU, paramBiFunction, paramBinaryOperator);
    }

    default <R> R collect(final Supplier<R> paramSupplier,
        final BiConsumer<R, ? super T> paramBiConsumer,
        final BiConsumer<R, R> paramBiConsumer1) {
        return stream().collect(paramSupplier, paramBiConsumer,
            paramBiConsumer1);
    }

    default <R, A> R collect(final Collector<? super T, A, R> paramCollector) {
        return stream().collect(paramCollector);
    }

    default Optional<T> min(final Comparator<? super T> paramComparator) {
        return stream().min(paramComparator);
    }

    default Optional<T> max(final Comparator<? super T> paramComparator) {
        return stream().max(paramComparator);
    }

    default long count() {
        return stream().count();
    }
}
