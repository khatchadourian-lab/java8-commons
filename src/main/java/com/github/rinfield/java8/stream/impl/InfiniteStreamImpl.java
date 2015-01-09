package com.github.rinfield.java8.stream.impl;

import java.util.stream.Stream;

import com.github.rinfield.java8.stream.InfiniteStream;

public class InfiniteStreamImpl<T> extends
    AbstractStream<T, InfiniteStream<T>> implements InfiniteStream<T> {

    public InfiniteStreamImpl(final Stream<T> stream) {
        super(stream);
    }
}