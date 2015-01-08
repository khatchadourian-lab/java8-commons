package com.github.rinfield.java8.stream;

import java.util.function.Predicate;
import java.util.stream.Stream;

import com.github.rinfield.java8.util.Tuple;

public interface StreamExtInter<T> {
    Stream<T> stream();

    Streamz<T> concat(Stream<? extends T> other);

    Streamz<T> concat(Streamz<? extends T> other);

    Streamz<T> append(T other);

    Streamz<Tuple<T, Integer>> zipWithIndex();

    Streamz<T> takeWhile(Predicate<T> predicate);

    Streamz<Streamz<T>> grouped(int step);
}
