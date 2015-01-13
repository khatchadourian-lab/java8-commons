package com.github.rinfield.java8.stream;

public class Item {

    public static boolean isNull(final Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(final Object obj) {
        return obj != null;
    }

    public static void println(final Object obj) {
        System.out.println(obj);
    }
}
