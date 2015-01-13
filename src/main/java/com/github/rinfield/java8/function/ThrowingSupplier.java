package com.github.rinfield.java8.function;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import com.github.rinfield.java8.util.Try;

@FunctionalInterface
public interface ThrowingSupplier<T, X extends Throwable> {

    T get() throws X;

    static <T, X extends Throwable> ThrowingSupplier<T, X> wrap(
        final ThrowingSupplier<T, X> f) {
        return f;
    }

    @SuppressWarnings("unchecked")
    default <U> Supplier<U> toSupplier(final Function<Try<T, X>, U> mapper) {
        return () -> {
            Try<T, X> trys;
            try {
                trys = Try.success(get());
            } catch (final Throwable e) {
                trys = Try.failure((X) e);
            }
            return mapper.apply(trys);
        };
    }

    default Supplier<T> rethrow() {
        return toSupplier(t -> t.orElseThrowUnchecked());
    }

    default Supplier<Optional<T>> optional() {
        return toSupplier(t -> t.toOption());
    }

    default Supplier<Try<T, X>> toTry() {
        return toSupplier(t -> t);
    }

    default Supplier<T> orElse(final T other) {
        return toSupplier(t -> t.orElse(other));
    }

    default Supplier<T> orElseGet(final Supplier<? extends T> other) {
        return toSupplier(t -> t.orElseGet(other));
    }

    default Supplier<T> orElseCatch(
        final Function<? super X, ? extends T> mapper) {
        return toSupplier(t -> t.orElseCatch(mapper));
    }
}