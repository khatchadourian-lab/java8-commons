package com.github.rinfield.java8.stream.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import com.github.rinfield.java8.stream.FiniteStream;

public class Functionz {

    public static <T> Function<T, Stream<FiniteStream<T>>> grouped(final int step) {
        return new GroupingFunction<>(step, Optional.empty(), Optional.empty());
    }

    public static <T> Function<T, Stream<FiniteStream<T>>> grouped(
        final int step, final int streamSize) {
        return new GroupingFunction<>(step, Optional.of(streamSize),
            Optional.empty());
    }

    public static <T> Function<T, Stream<FiniteStream<T>>> grouped(
        final int step, final int streamSize, final T padding) {
        return new GroupingFunction<>(step, Optional.of(streamSize),
            Optional.of(padding));
    }

    public static class GroupingFunction<T> implements
        Function<T, Stream<FiniteStream<T>>> {

        private final int step;
        private int currentIndex;
        private int consumedTotal = 0;
        private List<T> buffering;
        private final Optional<Integer> streamSizeOpt;
        private final Optional<T> paddingOpt;

        public GroupingFunction(final int step,
            final Optional<Integer> streamSize, final Optional<T> padding) {
            this.step = step;
            this.streamSizeOpt = streamSize;
            this.paddingOpt = padding;
            getAndReset();
        }

        @Override
        public Stream<FiniteStream<T>> apply(final T t) {
            final boolean isFilled = append(t);
            final boolean isLastElem = streamSizeOpt.filter(
                i -> i == consumedTotal).isPresent();
            if (isLastElem && paddingOpt.isPresent()) {
                while (buffering.size() < step) {
                    append(paddingOpt.get());
                }
            }
            return (isFilled || isLastElem) ? Stream.of(FiniteStream
                .of(getAndReset().stream())) : Stream.empty();
        }

        private List<T> getAndReset() {
            currentIndex = 0;
            final List<T> res = this.buffering;
            this.buffering = new ArrayList<>(step);
            return res;
        }

        private boolean append(final T t) {
            buffering.add(currentIndex, t);
            ++consumedTotal;
            ++currentIndex;
            return currentIndex >= step;
        }
    }
}
