package com.github.rinfield.java8.function;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import com.github.rinfield.java8.util.Try;

@FunctionalInterface
public interface ThrowingFunction<T, R> {

    R apply(T t) throws Throwable;

    static <T, R> ThrowingFunction<T, R> wrap(final ThrowingFunction<T, R> f) {
        return f;
    }

    static <T> ThrowingFunction<T, T> identity() {
        return t -> t;
    }

    default <U> Function<T, U> toFunction(
        final Function<Try<R>, ? extends U> mapper) {
        return t -> {
            Try<R> trys;
            try {
                trys = Try.success(apply(t));
            } catch (final Throwable e) {
                trys = Try.failure(e);
            }
            return mapper.apply(trys);
        };
    }

    default <U> ThrowingFunction<U, R> compose(
        final ThrowingFunction<? super U, ? extends T> before) {
        return (final U v) -> apply(before.apply(v));
    }

    default <U> ThrowingFunction<T, U> andThen(
        final ThrowingFunction<? super R, ? extends U> after) {
        return (final T t) -> after.apply(apply(t));
    }

    default Function<T, R> rethrow() {
        return toFunction(t -> t.orElseThrowUnchecked());
    }

    default Function<T, Optional<R>> optional() {
        return toFunction(t -> t.toOption());
    }

    default Function<T, R> orElseGet(final Supplier<? extends R> other) {
        return toFunction(t -> t.orElseGet(other));
    }

    default Function<T, R> catches(final Function<Throwable, ? extends R> mapper) {
        return toFunction(t -> t.orElseMap(mapper));
    }
}