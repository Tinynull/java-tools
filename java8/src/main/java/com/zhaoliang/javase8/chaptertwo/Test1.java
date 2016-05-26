package com.zhaoliang.javase8.chaptertwo;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * execise.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/5/25.
 */
public class Test1<T, R> {

    public static void main(String[] args) {
        int[] values = {1, 4, 9, 16};

        /**
         * 此意图是实际不符合，注意。
         */
        Stream<int[]> values1 = Stream.of(values);
        values1.forEach(System.out::println);

        /**
         * 这种方式是对的。
         */
        Arrays.stream(values).forEach(s -> System.out.println(s));

        /**
         * 这种方式最好。
         */
        IntStream values2 = IntStream.of(values);
        values2.forEach(s -> System.out.println(s));

        Test1<Integer, Long> t = new Test1<>();
        Integer test = t.test(t::value);
        System.out.println(test);

        Long aLong = t.test2(10, t::getTest2);
        System.out.println(aLong);

    }

    public Long getTest2(Integer integer) {
        return integer.longValue();
    }

    public Integer value() {
        return Integer.MAX_VALUE;
    }

    public T test(Supplier<T> s) {
        return s.get();
    }

    public R test2(T t, Function<T, R> function) {
        return function.apply(t);
    }


    public R testFunction(T t, R r, BiFunction<T, Integer, R> biFunction) {
        return biFunction.apply(t, 10);
    }

}
