package com.github.rinfield.java8.util;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractProduct implements Product {

    public static final int HASHCODE_PRIME = 31;

    public static final Optional<Boolean> ABSOLUTELY_TRUE = Optional.of(true);
    public static final Optional<Boolean> ABSOLUTELY_FALSE = Optional.of(false);
    public static final Optional<Boolean> UNDETERMINED = Optional.empty();

    @SuppressWarnings("unchecked")
    public static <T> boolean equalsSameClass(final T thiz, final Object that,
        final BiFunction<T, T, Boolean> f) {
        return equalsCommonPart(thiz, that).orElseGet(
            () -> f.apply(thiz, (T) that));
    }

    public static Optional<Boolean> equalsCommonPart(final Object x,
        final Object y) {
        if (x == y) {
            return ABSOLUTELY_TRUE;
        }
        if (x == null || y == null) {
            return ABSOLUTELY_FALSE;
        }
        if (x.getClass() != y.getClass()) {
            return ABSOLUTELY_FALSE;
        }
        return UNDETERMINED;
    }

    public static int hashCodeByStream(final Stream<?> stream) {
        return stream.map(x -> (x == null) ? 0 : x.hashCode()).reduce(1,
            (x, y) -> HASHCODE_PRIME * x + y);
    }

    public static String toStringByStream(final Class<?> clazz,
        final Stream<?> stream) {
        return clazz.getSimpleName() + "("
            + stream.map(Objects::toString).collect(Collectors.joining(","))
            + ")";
    }

    @Override
    public int hashCode() {
        return hashCodeByStream(stream());
    }

    @Override
    public boolean equals(final Object that) {
        return equalsSameClass(this, that, (x, y) -> x.list().equals(y.list()));
    }

    @Override
    public String toString() {
        return toStringByStream(this.getClass(), stream());
    }
}
