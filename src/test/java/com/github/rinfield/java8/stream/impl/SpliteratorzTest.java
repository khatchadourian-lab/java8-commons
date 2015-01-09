package com.github.rinfield.java8.stream.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.arrayContaining;
import static org.junit.Assert.assertThat;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

public class SpliteratorzTest {

    public Stream<Integer> infiniteStream() {
        return Stream.iterate(1, (i) -> ++i);
    }

    public Stream<Integer> finiteStream() {
        return infiniteStream().limit(8);
    }

    @Test
    public void takeWhileTest() {
        assertThat(Spliteratorz.takeWhile(finiteStream(), (i) -> i != 5)
            .toArray(), is(arrayContaining(1, 2, 3, 4)));

        assertThat(Spliteratorz.takeWhile(finiteStream(), (i) -> i != 10)
            .toArray(), is(arrayContaining(1, 2, 3, 4, 5, 6, 7, 8)));

        assertThat(Spliteratorz.takeWhile(infiniteStream(), (i) -> i != 5)
            .toArray(), is(arrayContaining(1, 2, 3, 4)));
    }

    @Test
    public void findTest() throws Exception {
        assertThat(Spliteratorz.find(finiteStream(), (i) -> i == 5),
            is(Optional.of(5)));

        assertThat(Spliteratorz.find(finiteStream(), (i) -> i == 99),
            is(Optional.empty()));

        assertThat(Spliteratorz.find(infiniteStream(), (i) -> i == 5),
            is(Optional.of(5)));
    }
}
