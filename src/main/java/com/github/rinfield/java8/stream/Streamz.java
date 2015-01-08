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
import java.util.stream.BaseStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Streamz<T> extends BaseStream<T, Streamz<T>>,
    StreamExtTerm<T>, StreamExtTermShort<T>, StreamExtInter<T>,
    StreamCompatTerm<T>, StreamCompatTermShort<T>, StreamCompatInter<T> {

    static <T> Streamz<T> of(final Stream<T> stream) {
        return new StreamzImpl<>(stream);
    }

    static <T> Streamz<T> of(final Collection<T> collection) {
        return of(collection.stream());
    }

    static <K, V> Streamz<Map.Entry<K, V>> of(final Map<K, V> map) {
        return of(map.entrySet());
    }

    static <T> Streamz<T> of(final Enumeration<T> enumeration) {
        return of(Collections.list(enumeration).stream());
    }

    static <T> Streamz<T> of(final Iterator<T> iterator) {
        return of(StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED),
            false));
    }

    @SuppressWarnings("unchecked")
    static <T> Streamz<T> of(final T... values) {
        return of(Stream.of(values));
    }

    static <T> Streamz<T> of(final T value) {
        return of(Stream.of(value));
    }

    static <T> Streamz<T> empty() {
        return of(Stream.empty());
    }

    static <T> Streamz<T> generate(final Supplier<T> s) {
        return of(Stream.generate(s));
    }

    static <T> Streamz<T> iterate(final T seed, final UnaryOperator<T> f) {
        return of(Stream.iterate(seed, f));
    }

}
