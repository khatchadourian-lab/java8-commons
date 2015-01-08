package com.github.rinfield.java8.stream;

import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterators.AbstractSpliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Spliteratorz {

    public static <T> Stream<T> takeWhile(final Stream<T> stream,
        final Predicate<? super T> predicate) {
        return StreamSupport.stream(new AbstractSpliterator<T>(Long.MAX_VALUE,
            0) {

            private final Iterator<T> iterator = stream.iterator();

            @Override
            public boolean tryAdvance(final Consumer<? super T> action) {
                if (!iterator.hasNext()) {
                    return false;
                }
                final T t = iterator.next();
                if (!predicate.test(t)) {
                    return false;
                }
                action.accept(t);
                return true;
            }
        }, false);
    }

    public static <T> Optional<T> find(final Stream<T> stream,
        final Predicate<? super T> predicate) {
        return StreamSupport.stream(
            new AbstractSpliterator<T>(Long.MAX_VALUE, 0) {

                private final Iterator<T> iterator = stream.iterator();

                @Override
                public boolean tryAdvance(final Consumer<? super T> action) {
                    while (iterator.hasNext()) {
                        final T t = iterator.next();
                        if (predicate.test(t)) {
                            action.accept(t);
                            return false;
                        }
                    }
                    return false;
                }
            }, false).findFirst();
    }
}
