package com.github.rinfield.java8.stream;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.BaseStream;
import java.util.stream.Stream;

public interface Streamz<T> extends BaseStream<T, Streamz<T>>,
    StreamExtTerm<T>, StreamExtTermShort<T>, StreamExtInter<T>,
    StreamCompatTerm<T>, StreamCompatTermShort<T>, StreamCompatInter<T> {

    static <T> Streamz<T> of(final Stream<T> stream) {
        return new StreamzImpl<>(stream);
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

    @SuppressWarnings("unchecked")
    static <T> Streamz<T> of(final T... values) {
        return of(Stream.of(values));
    }

    static <T> Streamz<T> of(final T value) {
        return of(Stream.of(value));
    }
}
