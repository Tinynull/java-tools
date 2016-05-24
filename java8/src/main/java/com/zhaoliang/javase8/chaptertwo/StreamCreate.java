package com.zhaoliang.javase8.chaptertwo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream create.
 *
 * Created by zhaoliang{weston_contribute@163.com} on 2016/5/24.
 */
public class StreamCreate {

    public static void main(String[] args) {
        StreamCreate streamCreate = new StreamCreate();
        streamCreate.test1();
    }

    public void test1() {
        List<String> list = new ArrayList<>();
        list.add("aaaa1");
        list.add("aaaaa2");
        list.add("aaaaaa3");
        list.add("aaaaaaa4");
        list.add("aaaaaaaa5");
        list.add("aaaaaaaaa6");

        List<String> collect = list.stream().filter(s -> s.length() > 6).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 该方法掩饰如何创建一个Stream.
     */
    public void streamCreate(){

        /**
         * of method.
         */
        Stream<String> stream = Stream.of("a,g,g,e,g,4".split(","));
        Stream<String> stream2 = Stream.of("aaa","bbb","ccc");

        /**
         * empty.
         */
        Stream<String> silence = Stream.empty();

        /**
         * 无限循环使用generate中的方法。
         */
        Stream<String> echos = Stream.generate(() -> "echo");
        Stream<Double> randoms = Stream.generate(Math::random);

        /**
         * 迭代。产生一个无限长的序列。
         */
        Stream<BigInteger> unlimit = Stream.iterate(BigInteger.ZERO,n -> n.add(BigInteger.ONE));

    }
}
