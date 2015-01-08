package com.github.rinfield.java8;

import java.util.Random;

import com.github.rinfield.java8.stream.Streamz;

public class FindStapCell {

    public static void main(final String[] args) {
        final Random random = new Random();
        Streamz.generate(() -> (char) (random.nextInt(26) + 65)).sequential()
            .grouped(4).map(Streamz::mkString).zipWithIndex().find(s -> {
                System.out.printf("%s細胞！\n", s.getKey());
                final boolean matched = s.getKey().equals("STAP");
                if (matched) {
                    System.out.printf("%s回目で陽性確認！よかった☆\n", s.getValue());
                }
                return matched;
            });
    }
}
