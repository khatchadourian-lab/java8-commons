package com.github.rinfield.java8.stream.ops.std;

import java.util.Optional;
import java.util.function.Predicate;

import com.github.rinfield.java8.stream.ops.StreamWrapper;

public interface StdShortTermOp<T, S extends StreamWrapper<T, S>> extends
    StreamWrapper<T, S> {

    default boolean anyMatch(final Predicate<? super T> paramPredicate) {
        return stream().anyMatch(paramPredicate);
    }

    default boolean allMatch(final Predicate<? super T> paramPredicate) {
        return stream().allMatch(paramPredicate);
    }

    default boolean noneMatch(final Predicate<? super T> paramPredicate) {
        return stream().noneMatch(paramPredicate);
    }

    default Optional<T> findFirst() {
        return stream().findFirst();
    }

    default Optional<T> findAny() {
        return stream().findAny();
    }
}
