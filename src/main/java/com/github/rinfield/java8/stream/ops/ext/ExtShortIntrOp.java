package com.github.rinfield.java8.stream.ops.ext;

import java.util.function.Predicate;

import com.github.rinfield.java8.stream.FiniteStream;
import com.github.rinfield.java8.stream.impl.Spliteratorz;
import com.github.rinfield.java8.stream.ops.StreamWrapper;

public interface ExtShortIntrOp<T, S extends StreamWrapper<T, S>> extends
    StreamWrapper<T, S> {

    default FiniteStream<T> takeWhile(final Predicate<T> predicate) {
        return FiniteStream.of(Spliteratorz.takeWhile(stream(), predicate));
    }
}
