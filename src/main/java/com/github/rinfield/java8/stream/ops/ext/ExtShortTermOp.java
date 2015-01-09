package com.github.rinfield.java8.stream.ops.ext;

import java.util.Optional;
import java.util.function.Predicate;

import com.github.rinfield.java8.stream.impl.Spliteratorz;
import com.github.rinfield.java8.stream.ops.StreamWrapper;

public interface ExtShortTermOp<T, S extends StreamWrapper<T, S>> extends
    StreamWrapper<T, S> {

    default Optional<T> find(final Predicate<? super T> predicate) {
        return Spliteratorz.find(stream(), predicate);
    }
}
