package com.github.rinfield.java8.stream;

import java.util.List;

public interface StreamExtTerm<T> {
    List<T> list();

    String mkString();

    String mkString(CharSequence delimiter);

    String mkString(CharSequence prefix, CharSequence delimiter,
        CharSequence suffix);
}
