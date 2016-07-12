package com.github.rinfield.java8.util;

import java.util.AbstractMap;
import java.util.Map;

public class Tuple<T1, T2> extends AbstractProduct {
    public final T1 _1;
    public final T2 _2;

    public static <T1, T2> Tuple<T1, T2> of(final T1 _1, final T2 _2) {
        return new Tuple<>(_1, _2);
    }

    public static <T1, T2> Tuple<T1, T2> of(final Map.Entry<T1, T2> entry) {
        return new Tuple<>(entry.getKey(), entry.getValue());
    }

    public Tuple(final T1 _1, final T2 _2) {
        this._1 = _1;
        this._2 = _2;
    }

    public Tuple<T2, T1> swap() {
        return of(_2, _1);
    }

    public Map.Entry<T1, T2> toEntry() {
        return new AbstractMap.SimpleImmutableEntry<>(_1, _2);
    }

    @Override
    public Object[] array() {
        return new Object[] { _1, _2 };
    }
}