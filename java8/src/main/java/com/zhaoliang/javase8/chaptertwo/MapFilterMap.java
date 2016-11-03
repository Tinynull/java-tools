package com.zhaoliang.javase8.chaptertwo;

import java.util.stream.Stream;

/**
 * functional method filter map.
 * Created by zhaoliang{weston_contribute@163.com} on 2016/5/24.
 */
public class MapFilterMap {
    public static void main(String[] args) {
        String test = "afDSSDF,FAS,AEGGA,GREGA,GRG,A,EGA";
        Stream<String> stream = Stream.of(test.split(","));
        stream.map(String::toLowerCase).forEach(System.out::println);
    }
}
