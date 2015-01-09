package com.github.rinfield.java8.stream.impl;

import java.util.stream.Stream;

import com.github.rinfield.java8.stream.FiniteStream;

public class FiniteStreamImpl<T> extends AbstractStream<T, FiniteStream<T>>
    implements FiniteStream<T> {

    public FiniteStreamImpl(final Stream<T> stream) {
        super(stream);
    }
}