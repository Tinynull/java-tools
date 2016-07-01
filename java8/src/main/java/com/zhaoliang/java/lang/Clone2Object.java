package com.zhaoliang.java.lang;

import java.io.Closeable;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/24.
 */
public class Clone2Object implements Cloneable {
    private int hello;

    public int getHello() {
        return hello;
    }

    public void setHello(int hello) {
        this.hello = hello;
    }

    @Override
    public String toString() {
        return "Clone2Object{" +
            "hello=" + hello +
            '}';
    }

    @Override
    protected Clone2Object clone() throws CloneNotSupportedException {
        return (Clone2Object) super.clone();
    }
}
