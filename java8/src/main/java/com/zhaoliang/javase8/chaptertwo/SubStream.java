package com.zhaoliang.javase8.chaptertwo;

import java.util.stream.Stream;

/**
 * 提取子流。
 *
 * Created by zhaoliang{weston_contribute@163.com} on 2016/5/24.
 */
public class SubStream {
    public static void main(String[] args) {

        /**
         * limit() 属于提取子流。
         */
        Stream.generate(Math::random).limit(100).forEach(System.out::println);

        /**
         * skip() 跳过前几个。
         */
        Stream.of("111","222","333").skip(1).forEach(System.out::println);

        /**
         * distinct() 去掉相同的。
         */
        Stream.generate(() -> "echo").limit(100).distinct().forEach(System.out::println);

        /**
         *
         */
        Stream.generate(Math::random).limit(100).sorted().forEach(System.out::println);
    }
}
