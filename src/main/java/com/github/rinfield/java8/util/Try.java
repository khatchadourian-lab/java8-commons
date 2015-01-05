package com.github.rinfield.java8.util;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface Try<T> {

    public enum Type {
        SUCCESS, FAILURE;
    }

    public static final class Success<T> implements Try<T> {
        private final T value;

        private Success(final T value) {
            this.value = value;
        }

        @Override
        public T get() {
            return value;
        }

        @Override
        public Throwable getException() {
            throw new NoSuchElementException();
        }

        @Override
        public T orElseGet(final Supplier<? extends T> other) {
            return value;
        }

        @Override
        public <X extends Throwable> T orElseThrow(
            final Function<? super Throwable, X> mapper) throws X {
            return value;
        }

        @Override
        public T orElseMap(final Function<? super Throwable, ? extends T> mapper) {
            return value;
        }

        @Override
        public void ifPresent(final Consumer<? super T> consumer) {
            consumer.accept(value);
        }

        @Override
        public void ifFailure(final Consumer<? super Throwable> consumer) {
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public Try<T> filter(final Predicate<? super T> predicate) {
            return predicate.test(value) ? this : new Failure<>();
        }

        @Override
        public <U> Try<U> map(final Function<? super T, ? extends U> mapper) {
            return new Try.Success<>(mapper.apply(value));
        }

        @Override
        public <U> Try<U> flatMap(final Function<? super T, Try<U>> mapper) {
            return mapper.apply(value);
        }

        @Override
        public Optional<T> toOption() {
            return Optional.of(value);
        }

        @Override
        public Try.Type type() {
            return Type.SUCCESS;
        }
    }

    public static final class Failure<T> implements Try<T> {

        private final Throwable exception;

        private Failure(final Throwable exception) {
            this.exception = exception;
        }

        private Failure() {
            this(new NoSuchElementException());
        }

        @Override
        public T get() {
            throw new NoSuchElementException();
        }

        @Override
        public Throwable getException() {
            return exception;
        }

        @Override
        public T orElseGet(final Supplier<? extends T> other) {
            return other.get();
        }

        @Override
        public <X extends Throwable> T orElseThrow(
            final Function<? super Throwable, X> mapper) throws X {
            throw mapper.apply(exception);
        }

        @Override
        public T orElseMap(final Function<? super Throwable, ? extends T> mapper) {
            return mapper.apply(exception);
        }

        @Override
        public void ifPresent(final Consumer<? super T> consumer) {
        }

        @Override
        public void ifFailure(final Consumer<? super Throwable> consumer) {
            consumer.accept(exception);
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public Try<T> filter(final Predicate<? super T> predicate) {
            return this;
        }

        @SuppressWarnings("unchecked")
        @Override
        public <U> Try<U> map(final Function<? super T, ? extends U> mapper) {
            return (Try<U>) this;
        }

        @SuppressWarnings("unchecked")
        @Override
        public <U> Try<U> flatMap(final Function<? super T, Try<U>> mapper) {
            return (Try<U>) this;
        }

        @Override
        public Optional<T> toOption() {
            return Optional.empty();
        }

        @Override
        public Try.Type type() {
            return Type.FAILURE;
        }
    }

    public static <T> Try<T> success(final T t) {
        return new Success<>(t);
    }

    static <T> Try<T> failure(final Throwable e) {
        return new Failure<>(e);
    }

    static <T> Try<T> failure() {
        return new Failure<>();
    }

    T get();

    Throwable getException();

    T orElseGet(final Supplier<? extends T> other);

    default T orElse(final T other) {
        return orElseGet(() -> other);
    }

    <X extends Throwable> T orElseThrow(Function<? super Throwable, X> mapper)
        throws X;

    default T orElseThrowUnchecked() {
        return orElseThrow(e -> new RuntimeException(e));
    }

    T orElseMap(Function<? super Throwable, ? extends T> mapper);

    void ifPresent(final Consumer<? super T> consumer);

    default void forEach(final Consumer<? super T> consumer) {
        ifPresent(consumer);
    }

    void ifFailure(final Consumer<? super Throwable> consumer);

    boolean isSuccess();

    default boolean isPresent() {
        return isSuccess();
    }

    default boolean isFailure() {
        return !isSuccess();
    }

    Try<T> filter(final Predicate<? super T> predicate);

    <U> Try<U> map(final Function<? super T, ? extends U> mapper);

    <U> Try<U> flatMap(final Function<? super T, Try<U>> mapper);

    Optional<T> toOption();

    Type type();
}