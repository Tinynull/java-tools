package com.zhaoliang.jackson.core;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/12/6.
 */
// Note: can use getters/setters as well; here we just use public fields directly:
public class MyValue {
    private String name;
    private int age;

    // NOTE: if using getters/setters, can keep fields `protected` or `private`
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
