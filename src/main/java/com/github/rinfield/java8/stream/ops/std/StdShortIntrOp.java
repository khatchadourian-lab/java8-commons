package com.github.rinfield.java8.stream.ops.std;

import com.github.rinfield.java8.stream.FiniteStream;
import com.github.rinfield.java8.stream.ops.StreamWrapper;

public interface StdShortIntrOp<T, S extends StreamWrapper<T, S>> extends
    StreamWrapper<T, S> {

    default FiniteStream<T> limit(final long paramLong) {
        return FiniteStream.of(stream().limit(paramLong));
    }
}
