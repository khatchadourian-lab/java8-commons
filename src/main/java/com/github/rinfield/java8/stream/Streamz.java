package com.github.rinfield.java8.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import java.util.function.UnaryOperator;
import java.util.stream.BaseStream;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public interface Streamz<T> extends BaseStream<T, Streamz<T>> {

    // ==== static Methods ====================================================

    static <T> Streamz<T> of(final Stream<T> stream) {
        return new StreamzImpl<>(stream);
    }

    static <T> Streamz<T> empty() {
        return of(Stream.empty());
    }

    static <T> Streamz<T> generate(final Supplier<T> s) {
        return of(Stream.generate(s));
    }

    static <T> Streamz<T> iterate(final T seed, final UnaryOperator<T> f) {
        return of(Stream.iterate(seed, f));
    }

    @SuppressWarnings("unchecked")
    static <T> Streamz<T> of(final T... values) {
        return of(Stream.of(values));
    }

    static <T> Streamz<T> of(final T value) {
        return of(Stream.of(value));
    }

    // ==== Extended Methods ==================================================

    Stream<T> stream();

    Streamz<T> concat(Stream<? extends T> other);

    Streamz<T> concat(Streamz<? extends T> other);

    Streamz<T> append(T other);

    List<T> list();

    Optional<T> find(Predicate<? super T> predicate);

    Streamz<Map.Entry<T, Integer>> zipWithIndex(); // TODO Tuple

    Streamz<T> takeWhile(Predicate<T> predicate);

    Streamz<Streamz<T>> grouped(int step);

    String mkString();

    String mkString(CharSequence delimiter);

    String mkString(CharSequence prefix, CharSequence delimiter,
        CharSequence suffix);

    // ==== Stream Methods ====================================================

    Streamz<T> filter(final Predicate<? super T> paramPredicate);

    <R> Streamz<R> map(final Function<? super T, ? extends R> paramFunction);

    IntStream mapToInt(final ToIntFunction<? super T> paramToIntFunction);

    LongStream mapToLong(final ToLongFunction<? super T> paramToLongFunction);

    DoubleStream mapToDouble(
        final ToDoubleFunction<? super T> paramToDoubleFunction);

    <R> Streamz<R> flatMap(
        final Function<? super T, ? extends Stream<? extends R>> paramFunction);

    IntStream flatMapToInt(
        final Function<? super T, ? extends IntStream> paramFunction);

    LongStream flatMapToLong(
        final Function<? super T, ? extends LongStream> paramFunction);

    DoubleStream flatMapToDouble(
        final Function<? super T, ? extends DoubleStream> paramFunction);

    Streamz<T> distinct();

    Streamz<T> sorted();

    Streamz<T> sorted(final Comparator<? super T> paramComparator);

    Streamz<T> peek(final Consumer<? super T> paramConsumer);

    Streamz<T> limit(final long paramLong);

    Streamz<T> skip(final long paramLong);

    void forEach(final Consumer<? super T> paramConsumer);

    void forEachOrdered(final Consumer<? super T> paramConsumer);

    Object[] toArray();

    <A> A[] toArray(final IntFunction<A[]> paramIntFunction);

    T reduce(final T paramT, final BinaryOperator<T> paramBinaryOperator);

    Optional<T> reduce(final BinaryOperator<T> paramBinaryOperator);

    <U> U reduce(final U paramU,
        final BiFunction<U, ? super T, U> paramBiFunction,
        final BinaryOperator<U> paramBinaryOperator);

    <R> R collect(final Supplier<R> paramSupplier,
        final BiConsumer<R, ? super T> paramBiConsumer,
        final BiConsumer<R, R> paramBiConsumer1);

    <R, A> R collect(final Collector<? super T, A, R> paramCollector);

    Optional<T> min(final Comparator<? super T> paramComparator);

    Optional<T> max(final Comparator<? super T> paramComparator);

    long count();

    boolean anyMatch(final Predicate<? super T> paramPredicate);

    boolean allMatch(final Predicate<? super T> paramPredicate);

    boolean noneMatch(final Predicate<? super T> paramPredicate);

    Optional<T> findFirst();

    Optional<T> findAny();
}
