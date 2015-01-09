package com.github.rinfield.java8.stream;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Streamz {
    public static <T> InfiniteStream<T> of(final Stream<T> stream) {
        return InfiniteStream.of(stream);
    }

    public static <T> FiniteStream<T> of(final Collection<T> collection) {
        return FiniteStream.of(collection.stream());
    }

    public static <K, V> FiniteStream<Map.Entry<K, V>> of(final Map<K, V> map) {
        return of(map.entrySet());
    }

    public static <T> FiniteStream<T> of(final Enumeration<T> enumeration) {
        return of(Collections.list(enumeration));
    }

    public static <T> InfiniteStream<T> of(final Iterator<T> iterator) {
        return of(StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED),
            false));
    }

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

    public static <T> InfiniteStream<T> iterate(final T seed,
        final UnaryOperator<T> f) {
        return of(Stream.iterate(seed, f));
    }
}
