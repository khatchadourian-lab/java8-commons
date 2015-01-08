package com.github.rinfield.java8.stream;

import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamzImpl<T> implements Streamz<T> {

    private final Stream<T> stream;

    public StreamzImpl(final Stream<T> stream) {
        this.stream = stream;
    }

    // ==== Extended Methods ==================================================
    @Override
    public Stream<T> stream() {
        return stream;
    }

    @Override
    public Streamz<T> concat(final Stream<? extends T> other) {
        return Streamz.of(Stream.concat(stream, other));
    }

    @Override
    public Streamz<T> concat(final Streamz<? extends T> other) {
        return concat(other.stream());
    }

    @Override
    public Streamz<T> append(final T other) {
        return concat(Stream.of(other));
    }

    @Override
    public List<T> list() {
        return stream.collect(Collectors.toList());
    }

    @Override
    public Optional<T> find(final Predicate<? super T> predicate) {
        return Spliteratorz.find(stream, predicate);
    }

    @Override
    public Streamz<Entry<T, Integer>> zipWithIndex() {
        return Streamz.of(stream.map(new Function<T, Entry<T, Integer>>() {
            private final AtomicInteger atomicInt = new AtomicInteger(0);

            @Override
            public Entry<T, Integer> apply(final T t) {
                return new SimpleEntry<>(t, atomicInt.getAndIncrement());
            }
        }));
    }

    @Override
    public Streamz<T> takeWhile(final Predicate<T> predicate) {
        return Streamz.of(Spliteratorz.takeWhile(stream, predicate));
    }

    @Override
    public Streamz<Streamz<T>> grouped(final int step) {
        return Streamz.of(stream.flatMap(FlatMapz.grouped(step)));
    }

    @Override
    public String mkString() {
        return stream.map(Object::toString).collect(Collectors.joining());
    }

    @Override
    public String mkString(final CharSequence delimiter) {
        return stream.map(Object::toString).collect(
            Collectors.joining(delimiter));
    }

    @Override
    public String mkString(final CharSequence prefix,
        final CharSequence delimiter, final CharSequence suffix) {
        return stream.map(Object::toString).collect(
            Collectors.joining(delimiter, prefix, suffix));
    }

    // ==== Stream Methods ====================================================
    @Override
    public Streamz<T> filter(final Predicate<? super T> paramPredicate) {
        return Streamz.of(stream.filter(paramPredicate));
    }

    @Override
    public <R> Streamz<R> map(
        final Function<? super T, ? extends R> paramFunction) {
        return Streamz.of(stream.map(paramFunction));
    }

    @Override
    public IntStream mapToInt(final ToIntFunction<? super T> paramToIntFunction) {
        return stream.mapToInt(paramToIntFunction);
    }

    @Override
    public LongStream mapToLong(
        final ToLongFunction<? super T> paramToLongFunction) {
        return stream.mapToLong(paramToLongFunction);
    }

    @Override
    public DoubleStream mapToDouble(
        final ToDoubleFunction<? super T> paramToDoubleFunction) {
        return stream.mapToDouble(paramToDoubleFunction);
    }

    @Override
    public <R> Streamz<R> flatMap(
        final Function<? super T, ? extends Stream<? extends R>> paramFunction) {
        return Streamz.of(stream.flatMap(paramFunction));
    }

    @Override
    public IntStream flatMapToInt(
        final Function<? super T, ? extends IntStream> paramFunction) {
        return stream.flatMapToInt(paramFunction);
    }

    @Override
    public LongStream flatMapToLong(
        final Function<? super T, ? extends LongStream> paramFunction) {
        return stream.flatMapToLong(paramFunction);
    }

    @Override
    public DoubleStream flatMapToDouble(
        final Function<? super T, ? extends DoubleStream> paramFunction) {
        return stream.flatMapToDouble(paramFunction);
    }

    @Override
    public Streamz<T> distinct() {
        return Streamz.of(stream.distinct());
    }

    @Override
    public Streamz<T> sorted() {
        return Streamz.of(stream.sorted());
    }

    @Override
    public Streamz<T> sorted(final Comparator<? super T> paramComparator) {
        return Streamz.of(stream.sorted(paramComparator));
    }

    @Override
    public Streamz<T> peek(final Consumer<? super T> paramConsumer) {
        return Streamz.of(stream.peek(paramConsumer));
    }

    @Override
    public Streamz<T> limit(final long paramLong) {
        return Streamz.of(stream.limit(paramLong));
    }

    @Override
    public Streamz<T> skip(final long paramLong) {
        return Streamz.of(stream.skip(paramLong));
    }

    @Override
    public void forEach(final Consumer<? super T> paramConsumer) {
        stream.forEach(paramConsumer);
    }

    @Override
    public void forEachOrdered(final Consumer<? super T> paramConsumer) {
        stream.forEachOrdered(paramConsumer);
    }

    @Override
    public Object[] toArray() {
        return stream.toArray();
    }

    @Override
    public <A> A[] toArray(final IntFunction<A[]> paramIntFunction) {
        return stream.toArray(paramIntFunction);
    }

    @Override
    public T reduce(final T paramT, final BinaryOperator<T> paramBinaryOperator) {
        return stream.reduce(paramT, paramBinaryOperator);
    }

    @Override
    public Optional<T> reduce(final BinaryOperator<T> paramBinaryOperator) {
        return stream.reduce(paramBinaryOperator);
    }

    @Override
    public <U> U reduce(final U paramU,
        final BiFunction<U, ? super T, U> paramBiFunction,
        final BinaryOperator<U> paramBinaryOperator) {
        return stream.reduce(paramU, paramBiFunction, paramBinaryOperator);
    }

    @Override
    public <R> R collect(final Supplier<R> paramSupplier,
        final BiConsumer<R, ? super T> paramBiConsumer,
        final BiConsumer<R, R> paramBiConsumer1) {
        return stream.collect(paramSupplier, paramBiConsumer, paramBiConsumer1);
    }

    @Override
    public <R, A> R collect(final Collector<? super T, A, R> paramCollector) {
        return stream.collect(paramCollector);
    }

    @Override
    public Optional<T> min(final Comparator<? super T> paramComparator) {
        return stream.min(paramComparator);
    }

    @Override
    public Optional<T> max(final Comparator<? super T> paramComparator) {
        return stream.max(paramComparator);
    }

    @Override
    public long count() {
        return stream.count();
    }

    @Override
    public boolean anyMatch(final Predicate<? super T> paramPredicate) {
        return stream.anyMatch(paramPredicate);
    }

    @Override
    public boolean allMatch(final Predicate<? super T> paramPredicate) {
        return stream.allMatch(paramPredicate);
    }

    @Override
    public boolean noneMatch(final Predicate<? super T> paramPredicate) {
        return stream.noneMatch(paramPredicate);
    }

    @Override
    public Optional<T> findFirst() {
        return stream.findFirst();
    }

    @Override
    public Optional<T> findAny() {
        return stream.findAny();
    }

    @Override
    public Iterator<T> iterator() {
        return stream.iterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return stream.spliterator();
    }

    @Override
    public boolean isParallel() {
        return stream.isParallel();
    }

    @Override
    public Streamz<T> sequential() {
        return Streamz.of(stream.sequential());
    }

    @Override
    public Streamz<T> parallel() {
        return Streamz.of(stream.parallel());
    }

    @Override
    public Streamz<T> unordered() {
        return Streamz.of(stream.unordered());
    }

    @Override
    public Streamz<T> onClose(final Runnable paramRunnable) {
        return Streamz.of(stream.onClose(paramRunnable));
    }

    @Override
    public void close() {
        stream.close();
    }
}
