package com.github.rinfield.java8.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.rinfield.java8.stream.Item;
import com.github.rinfield.java8.stream.Streamz;

public class ReadFile {

    public static void main(final String[] args) throws IOException {
        try (final BufferedReader br = Files.newBufferedReader(Paths
            .get("LICENSE.txt"))) {
            Streamz.generateSuppress(br::readLine).takeWhile(Item::isNotNull)
                .forEach(Item::println);
        }
    }
}
