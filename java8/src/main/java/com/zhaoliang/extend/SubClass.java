package com.zhaoliang.extend;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/7/4.
 */
public class SubClass implements SuperClass1,SuperClass2 {
    @Override
    public String hello() {
        return "hello";
    }

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        System.out.println(subClass.hello());
    }
}
