package com.github.rinfield.java8.stream.ops.ext;

import java.util.List;
import java.util.stream.Collectors;

import com.github.rinfield.java8.stream.ops.StreamWrapper;

public interface ExtOpenTermOp<T, S extends StreamWrapper<T, S>> extends
    StreamWrapper<T, S> {

    default List<T> list() {
        return stream().collect(Collectors.toList());
    }

    default String mkString() {
        return stream().map(Object::toString).collect(Collectors.joining());
    }

    default String mkString(final CharSequence delimiter) {
        return stream().map(Object::toString).collect(
            Collectors.joining(delimiter));
    }

    default String mkString(final CharSequence prefix,
        final CharSequence delimiter, final CharSequence suffix) {
        return stream().map(Object::toString).collect(
            Collectors.joining(delimiter, prefix, suffix));
    }
}
