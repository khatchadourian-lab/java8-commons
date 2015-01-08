package com.github.rinfield.java8.util;

public class Tuple<T1, T2> extends AbstractProduct {
    public final T1 _1;
    public final T2 _2;

    public static <A1, A2> Tuple<A1, A2> of(final A1 _1, final A2 _2) {
        return new Tuple<>(_1, _2);
    }

    public Tuple(final T1 _1, final T2 _2) {
        this._1 = _1;
        this._2 = _2;
    }

    public Tuple<T2, T1> swap() {
        return of(_2, _1);
    }

    @Override
    public int arity() {
        return 2;
    }

    @Override
    public Object[] array() {
        return new Object[] { _1, _2 };
    }
}