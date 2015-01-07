package com.github.rinfield.java8.util;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface Try<T, X extends Throwable> {

    public enum Result {
        SUCCESS, FAILURE;

        public boolean isSuccess() {
            return this == SUCCESS ? true : false;
        }

        public boolean isFailure() {
            return !isSuccess();
        }
    }

    public static final class Success<T, X extends Throwable> implements
        Try<T, X> {
        private final T value;

        private Success(final T value) {
            this.value = value;
        }

        @Override
        public T get() {
            return value;
        }

        @Override
        public X getException() {
            throw new NoSuchElementException();
        }

        @Override
        public Result resultType() {
            return Result.SUCCESS;
        }

        @Override
        public boolean isPresent() {
            return value != null;
        }

        @Override
        public T orElseGet(final Supplier<? extends T> other) {
            return value;
        }

        @Override
        public <Y extends Throwable> T orElseThrow(
            final Function<? super X, ? extends Y> mapper) throws Y {
            return value;
        }

        @Override
        public T orElseCatch(final Function<? super X, ? extends T> mapper) {
            return value;
        }

        @Override
        public void ifSuccess(final Consumer<? super T> consumer) {
            consumer.accept(value);
        }

        @Override
        public void ifSuccess(final Runnable runnable) {
            runnable.run();
        }

        @Override
        public void ifPresent(final Consumer<? super T> consumer) {
            if (isPresent()) {
                ifSuccess(consumer);
            }
        }

        @Override
        public void ifFailure(final Consumer<? super X> consumer) {
        }

        @Override
        public void ifFailure(final Runnable runnable) {
        }

        @Override
        public Try<T, X> filter(final Predicate<? super T> predicate) {
            return predicate.test(value) ? this : new Failure<>(null);
        }

        @Override
        public <U> Try<U, X> map(final Function<? super T, ? extends U> mapper) {
            return new Try.Success<>(mapper.apply(value));
        }

        @Override
        public <U> Try<U, X> flatMap(final Function<? super T, Try<U, X>> mapper) {
            return mapper.apply(value);
        }

        @Override
        public Optional<T> toOption() {
            return Optional.of(value);
        }
    }

    public static final class Failure<T, X extends Throwable> implements
        Try<T, X> {

        private final X exception;

        private Failure(final X exception) {
            this.exception = exception;
        }

        @Override
        public T get() {
            throw new NoSuchElementException();
        }

        @Override
        public X getException() {
            return exception;
        }

        @Override
        public Result resultType() {
            return Result.FAILURE;
        }

        @Override
        public boolean isPresent() {
            return false;
        }

        @Override
        public T orElseGet(final Supplier<? extends T> other) {
            return other.get();
        }

        @Override
        public <Y extends Throwable> T orElseThrow(
            final Function<? super X, ? extends Y> mapper) throws Y {
            throw mapper.apply(exception);
        }

        @Override
        public T orElseCatch(final Function<? super X, ? extends T> mapper) {
            return mapper.apply(exception);
        }

        @Override
        public void ifSuccess(final Consumer<? super T> consumer) {
        }

        @Override
        public void ifSuccess(final Runnable runnable) {
        }

        @Override
        public void ifPresent(final Consumer<? super T> consumer) {
        }

        @Override
        public void ifFailure(final Consumer<? super X> consumer) {
            consumer.accept(exception);
        }

        @Override
        public void ifFailure(final Runnable procedure) {
            procedure.run();
        }

        @Override
        public Try<T, X> filter(final Predicate<? super T> predicate) {
            return this;
        }

        @SuppressWarnings("unchecked")
        @Override
        public <U> Try<U, X> map(final Function<? super T, ? extends U> mapper) {
            return (Try<U, X>) this;
        }

        @SuppressWarnings("unchecked")
        @Override
        public <U> Try<U, X> flatMap(final Function<? super T, Try<U, X>> mapper) {
            return (Try<U, X>) this;
        }

        @Override
        public Optional<T> toOption() {
            return Optional.empty();
        }
    }

    static <T, X extends Throwable> Try<T, X> success(final T t) {
        return new Success<>(t);
    }

    static <X extends Throwable> Try<Void, X> success() {
        return new Success<>(null);
    }

    static <T, X extends Throwable> Try<T, X> failure(final X e) {
        return new Failure<>(e);
    }

    T get();

    X getException();

    Result resultType();

    default boolean isSuccess() {
        return resultType().isSuccess();
    }

    default boolean isFailure() {
        return resultType().isFailure();
    }

    boolean isPresent();

    T orElseGet(final Supplier<? extends T> other);

    default T orElse(final T other) {
        return orElseGet(() -> other);
    }

    <Y extends Throwable> T orElseThrow(Function<? super X, ? extends Y> mapper)
        throws Y;

    default T orElseThrow() throws X {
        return orElseThrow(x -> x);
    }

    default T orElseThrowUnchecked() {
        return orElseThrow(e -> new RuntimeException(e));
    }

    T orElseCatch(Function<? super X, ? extends T> mapper);

    void ifSuccess(final Consumer<? super T> consumer);

    void ifSuccess(final Runnable runnable);

    void ifPresent(final Consumer<? super T> consumer);

    default void forEach(final Consumer<? super T> consumer) {
        ifPresent(consumer);
    }

    void ifFailure(final Consumer<? super X> consumer);

    void ifFailure(final Runnable runnable);

    Try<T, X> filter(final Predicate<? super T> predicate);

    <U> Try<U, X> map(final Function<? super T, ? extends U> mapper);

    <U> Try<U, X> flatMap(final Function<? super T, Try<U, X>> mapper);

    Optional<T> toOption();
}
