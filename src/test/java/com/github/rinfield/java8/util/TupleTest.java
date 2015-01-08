package com.github.rinfield.java8.util;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;

public class TupleTest {

    @Test
    public void fieldShouldReturnSameInstance() throws Exception {
        final Integer _1 = new Integer(1);
        final Integer _2 = new Integer(2);
        final Tuple<Integer, Integer> tuple = Tuple.of(_1, _2);
        assertThat(tuple._1, is(sameInstance(_1)));
        assertThat(tuple._2, is(sameInstance(_2)));
    }

    @Test
    public void swapShouldSwapTypeAndValue() throws Exception {
        final Tuple<String, Integer> swapped = Tuple.of(1, "two").swap();
        assertThat(swapped._1, is("two"));
        assertThat(swapped._2, is(1));
    }

    @Test
    public void arityShouldReturn2() throws Exception {
        assertThat(Tuple.of(1, 2).arity(), is(2));
    }

    @Test
    public void arrayShouldReturnTwoElementArray() throws Exception {
        assertThat(Tuple.of(1, 2).array(), is(new Object[] { 1, 2 }));
    }

    @Test
    public void listShouldReturnTwoElementList() throws Exception {
        assertThat(Tuple.of(1, 2).list(), contains(1, 2));
    }

    @Test
    public void streamShouldReturnTwoElementStream() throws Exception {
        final Stream<Object> stream = Tuple.of(1, 2).stream();
        assertThat(stream.toArray(), is(new Object[] { 1, 2 }));
    }

    @Test
    public void hashCodeShouldObeyHashcodeRule() throws Exception {
        final Tuple<Integer, Integer> i1i2 = Tuple.of(1, 2);
        assertThat(i1i2.hashCode(), is(i1i2.hashCode()));
        assertThat(i1i2.hashCode(), is(not(Tuple.of(1, 3).hashCode())));
        assertThat(i1i2.hashCode(), is(not(Tuple.of(1, "2").hashCode())));
    }

    @Test
    public void toStringShouldReturnStringRepresentation() throws Exception {
        assertThat(Tuple.of(1, 2).toString(), is("Tuple(1,2)"));
        assertThat(Tuple.of(1, 3).toString(), is("Tuple(1,3)"));
        assertThat(Tuple.of(1, "2").toString(), is("Tuple(1,2)"));
    }
}
