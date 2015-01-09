package com.github.rinfield.java8.stream.ops;

import java.util.stream.Stream;

public interface StreamWrapper<T, S extends StreamWrapper<T, S>> {

    Stream<T> stream();

    S wrap(Stream<T> stream);
}
