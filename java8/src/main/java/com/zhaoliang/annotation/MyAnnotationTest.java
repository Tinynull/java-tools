package com.zhaoliang.annotation;

/**
 * test.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/17.
 */
public class MyAnnotationTest {

    @MyAnnotation
    public void info() {
        System.out.println("info");
    }

    @MyAnnotation(name = "test", age = 25)
    public void test() {
        System.out.println("test");
    }

    public void commonMethod(){

    }
}
