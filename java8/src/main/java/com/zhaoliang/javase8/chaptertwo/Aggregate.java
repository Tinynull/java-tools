package com.zhaoliang.javase8.chaptertwo;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 聚合方法。
 * <p>
 * Created by zhaoliang{weston_contribute@163.com} on 2016/5/24.
 */
public class Aggregate {
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("sagw", "anknbn", "hhhqh5", "jhthth");
        Optional<String> max = stringStream.max(String::compareToIgnoreCase);
        max.ifPresent(System.out::println);

        /*
          findAny() 在并行计算中非常有效，如果只要找到需要的类，则立马停止。
         */
        Stream<String> stringStream2 = Stream.of("sagw", "anknbn", "hhhqh5", "jhthth");
        Optional<String> a = stringStream2.filter(s -> s.startsWith("a")).findAny();
        a.ifPresent(System.out::println);
    }
}
