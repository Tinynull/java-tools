package com.zhaoliang.generic;

import static javafx.scene.input.KeyCode.T;

/**
 * 泛型。
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/16.
 */
public class Demo<E> {


    public E fun2(E t) {
        return t;
    }

    public <T> T fun(T t) {
        return t;
    }

    public <T extends Number> T fun3(T t) {
        return t;
    }


    public static void main(String[] args) {
        Demo<String> demo = new Demo<>();
        System.out.println("demo.fun(2.3) = " + demo.fun(2.3));
        System.out.println("demo.fun2(\"hello\") = " + demo.fun2("hello"));
        System.out.println("demo.fun3(\"hello\".hashCode()) = " + demo.fun3("hello".hashCode()));
    }


}
