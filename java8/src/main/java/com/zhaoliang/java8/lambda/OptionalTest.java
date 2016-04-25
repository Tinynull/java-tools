package com.zhaoliang.java8.lambda;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/23.
 */
public class OptionalTest {
    
    private static String test = "test";
    public static void main(String[] args) {
        System.out.println(test);
        System.out.println(test.equals("test"))
        System.out.print("hello");
        test.equals("hello" + test.substring(2) + " world");
        System.out.println(test.substring(1));
    }
}
