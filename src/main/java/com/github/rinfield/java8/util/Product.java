package com.github.rinfield.java8.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public interface Product {

    int arity();

    Object[] array();

    default List<Object> list() {
        return Arrays.asList(array());
    }

    default Stream<Object> stream() {
        return Stream.of(array());
    }
}
