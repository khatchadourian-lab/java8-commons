package com.github.rinfield.java8.stream.ops.std;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.github.rinfield.java8.stream.FiniteStream;
import com.github.rinfield.java8.stream.InfiniteStream;
import com.github.rinfield.java8.stream.ops.StreamWrapper;

public interface StdOpenIntrOp<T, S extends StreamWrapper<T, S>> extends
    StreamWrapper<T, S> {

    interface Finite<T> extends StreamWrapper<T, FiniteStream<T>> {

        default <R> FiniteStream<R> map(
            final Function<? super T, ? extends R> paramFunction) {
            return FiniteStream.of(stream().map(paramFunction));
        }

        default <R> FiniteStream<R> flatMap(
            final Function<? super T, ? extends Stream<? extends R>> paramFunction) {
            return FiniteStream.of(stream().flatMap(paramFunction));
        }
    }

    interface Infinite<T> extends StreamWrapper<T, InfiniteStream<T>> {

        default <R> InfiniteStream<R> map(
            final Function<? super T, ? extends R> paramFunction) {
            return InfiniteStream.of(stream().map(paramFunction));
        }

        default <R> InfiniteStream<R> flatMap(
            final Function<? super T, ? extends Stream<? extends R>> paramFunction) {
            return InfiniteStream.of(stream().flatMap(paramFunction));
        }
    }

    default S filter(final Predicate<? super T> paramPredicate) {
        return wrap(stream().filter(paramPredicate));
    }

    default S distinct() {
        return wrap(stream().distinct());
    }

    default S sorted() {
        return wrap(stream().sorted());
    }

    default S sorted(final Comparator<? super T> paramComparator) {
        return wrap(stream().sorted(paramComparator));
    }

    default S peek(final Consumer<? super T> paramConsumer) {
        return wrap(stream().peek(paramConsumer));
    }

    default S skip(final long paramLong) {
        return wrap(stream().skip(paramLong));
    }

    default IntStream mapToInt(final ToIntFunction<? super T> paramToIntFunction) {
        return stream().mapToInt(paramToIntFunction);
    }

    default LongStream mapToLong(
        final ToLongFunction<? super T> paramToLongFunction) {
        return stream().mapToLong(paramToLongFunction);
    }

    default DoubleStream mapToDouble(
        final ToDoubleFunction<? super T> paramToDoubleFunction) {
        return stream().mapToDouble(paramToDoubleFunction);
    }

    default IntStream flatMapToInt(
        final Function<? super T, ? extends IntStream> paramFunction) {
        return stream().flatMapToInt(paramFunction);
    }

    default LongStream flatMapToLong(
        final Function<? super T, ? extends LongStream> paramFunction) {
        return stream().flatMapToLong(paramFunction);
    }

    default DoubleStream flatMapToDouble(
        final Function<? super T, ? extends DoubleStream> paramFunction) {
        return stream().flatMapToDouble(paramFunction);
    }
}
