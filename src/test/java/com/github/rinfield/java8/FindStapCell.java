package com.github.rinfield.java8;

import java.util.Random;

import com.github.rinfield.java8.stream.FiniteStream;
import com.github.rinfield.java8.stream.Streamz;

public class FindStapCell {

    public static void main(final String[] args) {
        final Random random = new Random();
        Streamz.generate(() -> (char) (random.nextInt(26) + 65)).sequential()
            .grouped(4).map(FiniteStream::mkString)
            .peek(s -> System.out.println(s + "細胞！")).zipWithIndex()
            .find(t -> t._1.equals("STAP"))
            .ifPresent(t -> System.out.printf("%s回目で陽性確認！よかった☆\n", t._2));
    }
}
