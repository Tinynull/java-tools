package com.zhaoliang.java8.random;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/29.
 */
public class RandomTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Random random = new Random();
        IntStream intStream1 = random.ints(0, 100);
        intStream1.limit(10).forEach(System.out::println);

        IntStream intStream = random.ints(0, 19);
        List<Integer> randomBetween0And99 = intStream
            .limit(100)
            .boxed()
            .collect(Collectors.toList());
        intStream.close();
        randomBetween0And99.stream().forEach(System.out::println);
    }

    private static void test2(){}
}
