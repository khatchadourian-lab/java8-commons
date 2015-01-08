package com.github.rinfield.java8.stream;

import static com.github.rinfield.java8.stream.FlatMapz.grouped;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class FlatMapsTest {

    @Test
    public void groupTest() {
        assertThat(
            finiteStream(5).flatMap(grouped(1)).map(xs -> xs.list().toString())
                .collect(Collectors.toList()).toString(),
            is("[[1], [2], [3], [4], [5]]"));

        assertThat(
            finiteStream(5).flatMap(grouped(2)).map(xs -> xs.list().toString())
                .collect(Collectors.toList()).toString(),
            is("[[1, 2], [3, 4]]"));

        assertThat(
            finiteStream(5).flatMap(grouped(2, 5))
                .map(xs -> xs.list().toString()).collect(Collectors.toList())
                .toString(), is("[[1, 2], [3, 4], [5]]"));

        assertThat(
            finiteStream(5).flatMap(grouped(2, 5, -1))
                .map(xs -> xs.list().toString()).collect(Collectors.toList())
                .toString(), is("[[1, 2], [3, 4], [5, -1]]"));

    }

    public static Stream<Integer> finiteStream(final int size) {
        return Stream.iterate(1, (i) -> ++i).limit(size);
    }
}
