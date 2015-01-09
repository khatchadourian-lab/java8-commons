package com.github.rinfield.java8.stream;

import java.util.stream.BaseStream;
import java.util.stream.Stream;

import com.github.rinfield.java8.stream.impl.InfiniteStreamImpl;
import com.github.rinfield.java8.stream.ops.CommonStreamOp;
import com.github.rinfield.java8.stream.ops.ext.ExtOpenIntrOp;
import com.github.rinfield.java8.stream.ops.std.StdOpenIntrOp;

public interface InfiniteStream<T> extends BaseStream<T, InfiniteStream<T>>,
    CommonStreamOp<T, InfiniteStream<T>>, StdOpenIntrOp.Infinite<T>,
    ExtOpenIntrOp.Infinite<T> {

    static <T> InfiniteStream<T> of(final Stream<T> stream) {
        return new InfiniteStreamImpl<>(stream);
    }

    default InfiniteStream<T> wrap(final Stream<T> stream) {
        return of(stream);
    }

    default FiniteStream<T> asSized() {
        return FiniteStream.of(stream());
    };
}
