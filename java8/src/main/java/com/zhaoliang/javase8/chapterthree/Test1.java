package com.zhaoliang.javase8.chapterthree;

import java.util.function.IntConsumer;

/**
 * lambda 表达式参数.
 * Created by zhaoliang(weston_contribute@163.com) on 2016/5/25.
 */
public class Test1 {
    public static void main(String[] args) {
        repeat(10, i -> System.out.println("count:" + i));
    }

    private static void repeat(int n, IntConsumer intConsumer) {
        for (int i = 0; i < n; i++) {
            intConsumer.accept(i);
        }
    }
}
