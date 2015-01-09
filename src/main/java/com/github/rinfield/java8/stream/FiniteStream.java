package com.github.rinfield.java8.stream;

import java.util.stream.BaseStream;
import java.util.stream.Stream;

import com.github.rinfield.java8.stream.impl.FiniteStreamImpl;
import com.github.rinfield.java8.stream.ops.CommonStreamOp;
import com.github.rinfield.java8.stream.ops.ext.ExtOpenIntrOp;
import com.github.rinfield.java8.stream.ops.ext.ExtOpenTermOp;
import com.github.rinfield.java8.stream.ops.std.StdOpenIntrOp;
import com.github.rinfield.java8.stream.ops.std.StdOpenTermOp;

public interface FiniteStream<T> extends BaseStream<T, FiniteStream<T>>,
    CommonStreamOp<T, FiniteStream<T>>, StdOpenIntrOp.Finite<T>,
    ExtOpenIntrOp.Finite<T>, StdOpenTermOp<T, FiniteStream<T>>,
    ExtOpenTermOp<T, FiniteStream<T>> {

    static <T> FiniteStream<T> of(final Stream<T> stream) {
        return new FiniteStreamImpl<>(stream);
    }

    default FiniteStream<T> wrap(final Stream<T> stream) {
        return of(stream);
    }
}
