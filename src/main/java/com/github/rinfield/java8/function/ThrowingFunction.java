package com.github.rinfield.java8.function;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import com.github.rinfield.java8.util.Try;

@FunctionalInterface
public interface ThrowingFunction<T, R, X extends Throwable> {

    R apply(T t) throws X;

    static <T, R, X extends Throwable> ThrowingFunction<T, R, X> wrap(
        final ThrowingFunction<T, R, X> f) {
        return f;
    }

    static <T, X extends Throwable> ThrowingFunction<T, T, X> identity() {
        return t -> t;
    }

    @SuppressWarnings("unchecked")
    default <U> Function<T, U> toFunction(
        final Function<Try<R, X>, ? extends U> mapper) {
        return t -> {
            Try<R, X> trys;
            try {
                trys = Try.success(apply(t));
            } catch (final Throwable e) {
                trys = Try.failure((X) e);
            }
            return mapper.apply(trys);
        };
    }

    default <U> ThrowingFunction<U, R, X> compose(
        final ThrowingFunction<? super U, ? extends T, ? extends X> before) {
        return (final U v) -> apply(before.apply(v));
    }

    default <U> ThrowingFunction<T, U, X> andThen(
        final ThrowingFunction<? super R, ? extends U, ? extends X> after) {
        return (final T t) -> after.apply(apply(t));
    }

    default Function<T, R> rethrow() {
        return toFunction(t -> t.orElseThrowUnchecked());
    }

    default Function<T, Optional<R>> optional() {
        return toFunction(t -> t.toOption());
    }

    default Function<T, Try<R, X>> toTry() {
        return toFunction(t -> t);
    }

    default Function<T, R> orElse(final R other) {
        return toFunction(t -> t.orElse(other));
    }

    default Function<T, R> orElseGet(final Supplier<? extends R> other) {
        return toFunction(t -> t.orElseGet(other));
    }

    default Function<T, R> orElseCatch(
        final Function<? super X, ? extends R> mapper) {
        return toFunction(t -> t.orElseCatch(mapper));
    }
}