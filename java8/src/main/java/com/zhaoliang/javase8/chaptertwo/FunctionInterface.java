package com.zhaoliang.javase8.chaptertwo;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 函数式接口使用。
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/5/26.
 */
public class FunctionInterface {

    private static Function<Integer, Integer> power = x -> x * x;

    private static Integer changeNumber(Integer integer, Function<Integer, Integer> function) {
        return function.apply(integer);
    }

    private static Integer changeNumber2(int a, String b, BiFunction<Integer, String, Integer> biFunction) {
        return biFunction.apply(a, b);
    }

    private static boolean isTrue(Integer a, Integer b, Predicate<Integer> predicate) {
        return predicate.test(a) && predicate.test(b);
    }

    public static void main(String[] args) {
        System.out.println(changeNumber(10, x -> x * 10 + 10));
        System.out.println(changeNumber(1110, power));
        System.out.println(changeNumber2(12, "sdf", (x, y) -> x * y.length()));
        System.out.println(isTrue(2, 5, x -> x < 5));
    }
}
