package com.zhaoliang.java8.clone;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/7/1.
 */
public class CloneableObject implements Cloneable {
    private int age;

    public CloneableObject(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public CloneableObject clone() throws CloneNotSupportedException {
        return (CloneableObject) super.clone();
    }

    @Override
    public String toString() {
        return "CloneableObject{" +
            "age=" + age +
            '}';
    }
}
