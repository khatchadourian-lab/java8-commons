package com.github.rinfield.java8.stream;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collector;

public interface StreamCompatTerm<T> {
    void forEach(final Consumer<? super T> paramConsumer);

    void forEachOrdered(final Consumer<? super T> paramConsumer);

    Object[] toArray();

    <A> A[] toArray(final IntFunction<A[]> paramIntFunction);

    T reduce(final T paramT, final BinaryOperator<T> paramBinaryOperator);

    Optional<T> reduce(final BinaryOperator<T> paramBinaryOperator);

    <U> U reduce(final U paramU,
        final BiFunction<U, ? super T, U> paramBiFunction,
        final BinaryOperator<U> paramBinaryOperator);

    <R> R collect(final Supplier<R> paramSupplier,
        final BiConsumer<R, ? super T> paramBiConsumer,
        final BiConsumer<R, R> paramBiConsumer1);

    <R, A> R collect(final Collector<? super T, A, R> paramCollector);

    Optional<T> min(final Comparator<? super T> paramComparator);

    Optional<T> max(final Comparator<? super T> paramComparator);

    long count();
}
