package com.github.rinfield.java8.stream;

import java.util.Optional;
import java.util.function.Predicate;

public interface StreamExtTermShort<T> {
    Optional<T> find(Predicate<? super T> predicate);
}
