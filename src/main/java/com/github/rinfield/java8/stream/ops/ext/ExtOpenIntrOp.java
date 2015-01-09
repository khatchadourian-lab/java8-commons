package com.github.rinfield.java8.stream.ops.ext;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import com.github.rinfield.java8.stream.FiniteStream;
import com.github.rinfield.java8.stream.InfiniteStream;
import com.github.rinfield.java8.stream.Streamz;
import com.github.rinfield.java8.stream.impl.Functionz;
import com.github.rinfield.java8.stream.ops.StreamWrapper;
import com.github.rinfield.java8.util.Tuple;

public interface ExtOpenIntrOp<T, S extends StreamWrapper<T, S>> extends
    StreamWrapper<T, S> {

    interface Finite<T> extends StreamWrapper<T, FiniteStream<T>> {

        default FiniteStream<Tuple<T, Integer>> zipWithIndex() {
            final AtomicInteger atomicInt = new AtomicInteger(0);
            return FiniteStream.of(stream().map(
                t -> Tuple.of(t, atomicInt.getAndIncrement())));
        }

        default FiniteStream<FiniteStream<T>> grouped(final int step) {
            return FiniteStream.of(stream().flatMap(Functionz.grouped(step)));
        }
    }

    interface Infinite<T> extends StreamWrapper<T, InfiniteStream<T>> {

        default InfiniteStream<Tuple<T, Integer>> zipWithIndex() {
            final AtomicInteger atomicInt = new AtomicInteger(0);
            return InfiniteStream.of(stream().map(
                t -> Tuple.of(t, atomicInt.getAndIncrement())));
        }

        default InfiniteStream<FiniteStream<T>> grouped(final int step) {
            return InfiniteStream.of(stream().flatMap(Functionz.grouped(step)));
        }
    }

    default S concat(final FiniteStream<? extends T> other) {
        return wrap(Stream.concat(stream(), other.stream()));
    }

    default S append(final T other) {
        return wrap(Stream.concat(stream(), Stream.of(other)));
    }

    default InfiniteStream<T> concat(final Stream<? extends T> other) {
        return Streamz.of(Stream.concat(stream(), other));
    }

    default InfiniteStream<T> concat(final InfiniteStream<? extends T> other) {
        return concat(other.stream());
    }
}
