package com.github.rinfield.java8.stream.impl;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Stream;

import com.github.rinfield.java8.stream.ops.StreamWrapper;

public abstract class AbstractStream<T, S extends StreamWrapper<T, S>>
    implements StreamWrapper<T, S> {

    protected Stream<T> stream;

    public AbstractStream(final Stream<T> stream) {
        this.stream = stream;
    }

    @Override
    public Stream<T> stream() {
        return stream;
    }

    // ==== BaseStream Operations =============================================
    public S sequential() {
        return wrap(stream().sequential());
    }

    public S parallel() {
        return wrap(stream().parallel());
    }

    public S unordered() {
        return wrap(stream().unordered());
    }

    public S onClose(final Runnable paramRunnable) {
        return wrap(stream().onClose(paramRunnable));
    }

    public Iterator<T> iterator() {
        return stream.iterator();
    }

    public Spliterator<T> spliterator() {
        return stream.spliterator();
    }

    public boolean isParallel() {
        return stream.isParallel();
    }

    public void close() {
        stream.close();
    }
}
