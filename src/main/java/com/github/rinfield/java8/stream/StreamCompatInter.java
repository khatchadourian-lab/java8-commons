package com.github.rinfield.java8.stream;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public interface StreamCompatInter<T> {
    Streamz<T> filter(final Predicate<? super T> paramPredicate);

    <R> Streamz<R> map(final Function<? super T, ? extends R> paramFunction);

    IntStream mapToInt(final ToIntFunction<? super T> paramToIntFunction);

    LongStream mapToLong(final ToLongFunction<? super T> paramToLongFunction);

    DoubleStream mapToDouble(
        final ToDoubleFunction<? super T> paramToDoubleFunction);

    <R> Streamz<R> flatMap(
        final Function<? super T, ? extends Stream<? extends R>> paramFunction);

    IntStream flatMapToInt(
        final Function<? super T, ? extends IntStream> paramFunction);

    LongStream flatMapToLong(
        final Function<? super T, ? extends LongStream> paramFunction);

    DoubleStream flatMapToDouble(
        final Function<? super T, ? extends DoubleStream> paramFunction);

    Streamz<T> distinct();

    Streamz<T> sorted();

    Streamz<T> sorted(final Comparator<? super T> paramComparator);

    Streamz<T> peek(final Consumer<? super T> paramConsumer);

    Streamz<T> limit(final long paramLong);

    Streamz<T> skip(final long paramLong);

}
