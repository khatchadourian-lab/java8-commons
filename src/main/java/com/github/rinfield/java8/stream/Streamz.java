package com.github.rinfield.java8.stream;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.github.rinfield.java8.function.ThrowingFunction;
import com.github.rinfield.java8.function.ThrowingSupplier;
import com.github.rinfield.java8.util.Tuple;

public final class Streamz {

    @SuppressWarnings("unchecked")
    public static <T> FiniteStream<T> of(final T... values) {
        return FiniteStream.of(Stream.of(values));
    }

    public static <T> FiniteStream<T> of(final T value) {
        return FiniteStream.of(Stream.of(value));
    }

    public static <T> FiniteStream<T> empty() {
        return FiniteStream.of(Stream.empty());
    }

    public static <T> InfiniteStream<T> generate(final Supplier<T> s) {
        return of(Stream.generate(s));
    }

    public static <T> InfiniteStream<T> generateSuppress(
        final ThrowingSupplier<T, ?> s) {
        return generate(s.rethrow());
    }

    public static <T> InfiniteStream<T> iterate(final T seed,
        final UnaryOperator<T> f) {
        return of(Stream.iterate(seed, f));
    }

    // ========================================================================
    public static <T> InfiniteStream<T> of(final Stream<T> stream) {
        return InfiniteStream.of(stream);
    }

    public static <T> FiniteStream<T> of(final Collection<T> collection) {
        return FiniteStream.of(collection.stream());
    }

    public static <K, V> FiniteStream<Tuple<K, V>> of(final Map<K, V> map) {
        return FiniteStream.of(map.entrySet().stream().map(Tuple::of));
    }

    public static <T> FiniteStream<T> of(final Enumeration<T> enumeration) {
        return of(Collections.list(enumeration));
    }

    public static <T> InfiniteStream<T> of(final Iterator<T> iterator) {
        return of(StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED),
            false));
    }

    public static <T, S> InfiniteStream<T> generate(final S seed,
        final Function<S, T> f) {
        return generate(() -> f.apply(seed));
    }

    public static <T, S> InfiniteStream<T> generateSuppress(final S seed,
        final ThrowingFunction<S, T, ?> f) {
        return generate(() -> f.rethrow().apply(seed));
    }

    public static InfiniteStream<Void> loop() {
        return generate(() -> null);
    }

    public static FiniteStream<Void> loop(final int count) {
        return loop().limit(count);
    }

    public static FiniteStream<Integer> range(final int start,
        final int endExclusive) {
        return range(start, endExclusive, 1);
    }

    public static FiniteStream<Integer> range(final int start,
        final int endExclusive, final int step) {
        return generate(new AtomicInteger(start), c -> c.getAndAdd(step))
            .takeWhile(i -> i < endExclusive);
    }
}
