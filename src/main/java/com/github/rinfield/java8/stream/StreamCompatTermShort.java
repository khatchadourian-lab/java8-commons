package com.github.rinfield.java8.stream;

import java.util.Optional;
import java.util.function.Predicate;

public interface StreamCompatTermShort<T> {
    boolean anyMatch(final Predicate<? super T> paramPredicate);

    boolean allMatch(final Predicate<? super T> paramPredicate);

    boolean noneMatch(final Predicate<? super T> paramPredicate);

    Optional<T> findFirst();

    Optional<T> findAny();
}
