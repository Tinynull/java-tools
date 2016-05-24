package com.zhaoliang.javase8.chapterone;

/**
 * Created by zhaoliang{weston_contribute@163.com} on 2016/5/24.
 */
public class Test9 {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(16);
        list.forEachIf(Integer::doubleValue, x -> x > 14);
        System.out.println(list);
        list.forEachIf(integer -> Integer.hashCode(integer), x -> x > 14);
    }
}
