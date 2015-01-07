package com.github.rinfield.java8.util;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public enum Flag {

    TRUE() {
        @Override
        public boolean isTrue() {
            return true;
        }

        @Override
        public boolean isFalse() {
            return false;
        }

        @Override
        public Flag not() {
            return FALSE;
        }

        @Override
        public Flag and(final Flag other, final Flag... others) {
            final boolean hasFalses = Stream.concat(Stream.of(other),
                Stream.of(others)).anyMatch(b -> b.isFalse());
            return Flag.of(hasFalses).not();
        }

        @Override
        public Flag or(final Flag other, final Flag... others) {
            return this;
        }

        @Override
        public void ifTrue(final Runnable ifTrue) {
            ifTrue.run();
        }

        @Override
        public void ifFalse(final Runnable ifFalse) {
        }

        @Override
        public void either(final Runnable ifTrue, final Runnable ifFalse) {
            ifTrue.run();
        }

        @Override
        public <T> Optional<T> ifTrue(final T ifTrue) {
            return Optional.of(ifTrue);
        }

        @Override
        public <T> Optional<T> ifFalse(final T ifFalse) {
            return Optional.empty();
        }

        @Override
        public <T> T either(final T ifTrue, final T ifFalse) {
            return ifTrue;
        }

        @Override
        public <T> Optional<T> ifTrue(final Supplier<? extends T> ifTrue) {
            return Optional.of(ifTrue.get());
        }

        @Override
        public <T> Optional<T> ifFalse(final Supplier<? extends T> ifFalse) {
            return Optional.empty();
        }

        @Override
        public <T> T either(final Supplier<? extends T> ifTrue,
            final Supplier<? extends T> ifFalse) {
            return ifTrue.get();
        }
    },

    FALSE() {
        @Override
        public boolean isTrue() {
            return false;
        }

        @Override
        public boolean isFalse() {
            return true;
        }

        @Override
        public Flag not() {
            return TRUE;
        }

        @Override
        public Flag and(final Flag other, final Flag... others) {
            return FALSE;
        }

        @Override
        public Flag or(final Flag other, final Flag... others) {
            final boolean hasTrues = Stream.concat(Stream.of(other),
                Stream.of(others)).anyMatch(b -> b.isTrue());
            return Flag.of(hasTrues);
        }

        @Override
        public void ifTrue(final Runnable ifTrue) {
        }

        @Override
        public void ifFalse(final Runnable ifFalse) {
            ifFalse.run();
        }

        @Override
        public void either(final Runnable ifTrue, final Runnable ifFalse) {
            ifFalse.run();
        }

        @Override
        public <T> Optional<T> ifTrue(final T ifTrue) {
            return Optional.empty();
        }

        @Override
        public <T> Optional<T> ifFalse(final T ifFalse) {
            return Optional.of(ifFalse);
        }

        @Override
        public <T> T either(final T ifTrue, final T ifFalse) {
            return ifFalse;
        }

        @Override
        public <T> Optional<T> ifTrue(final Supplier<? extends T> ifTrue) {
            return Optional.empty();
        }

        @Override
        public <T> Optional<T> ifFalse(final Supplier<? extends T> ifFalse) {
            return Optional.of(ifFalse.get());
        }

        @Override
        public <T> T either(final Supplier<? extends T> ifTrue,
            final Supplier<? extends T> ifFalse) {
            return ifFalse.get();
        }
    };

    public static Flag of(final boolean bool) {
        return bool ? TRUE : FALSE;
    }

    public abstract boolean isTrue();

    public abstract boolean isFalse();

    public abstract Flag not();

    public abstract Flag and(Flag other, Flag... others);

    public abstract Flag or(Flag other, Flag... others);

    public abstract void ifTrue(final Runnable ifTrue);

    public abstract void ifFalse(final Runnable ifFalse);

    public abstract void either(final Runnable ifTrue, final Runnable ifFalse);

    public abstract <T> Optional<T> ifTrue(T ifTrue);

    public abstract <T> Optional<T> ifFalse(T ifFalse);

    public abstract <T> T either(T ifTrue, T ifFalse);

    public abstract <T> Optional<T> ifTrue(Supplier<? extends T> ifTrue);

    public abstract <T> Optional<T> ifFalse(Supplier<? extends T> ifFalse);

    public abstract <T> T either(final Supplier<? extends T> ifTrue,
        final Supplier<? extends T> ifFalse);
}
