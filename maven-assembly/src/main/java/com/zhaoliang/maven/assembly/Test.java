package com.zhaoliang.maven.assembly;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/7/14.
 */
public class Test {
    public Test(){}

    private static String config = "hello.properties";

    public static void main(String[] args) {
        System.out.println("hello");
        System.out.println(config);
        test1();
    }

    private static void test1(){
        System.out.println("test1");
    }
    public static void test2(){
        System.out.println("test2");
    }
}
