package com.zhaoliang.javase8.chaptertwo;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * stream create.
 * <p>
 * Created by zhaoliang{weston_contribute@163.com} on 2016/5/24.
 */
public class StreamCreate {

    public static void main(String[] args) {
        Stream.generate(() -> "echo").limit(100).forEach(System.out::println);
    }

    /**
     * 该方法演示如何创建一个Stream.
     */
    public static void streamCreate() {

        /**
         * of method.
         */
        Stream<String> stream = Stream.of("a,g,g,e,g,4".split(","));
        Stream<String> stream2 = Stream.of("aaa", "bbb", "ccc");

        /**
         * empty.
         */
        Stream<String> silence = Stream.empty();

        /**
         * 无限循环使用generate中的方法。
         */
        Stream<String> echos = Stream.generate(() -> "echo").limit(3000);
        Stream<Double> randoms = Stream.generate(Math::random);

        /**
         * 迭代。产生一个无限长的序列。
         */
        Stream<BigInteger> unlimit = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));

    }
}
