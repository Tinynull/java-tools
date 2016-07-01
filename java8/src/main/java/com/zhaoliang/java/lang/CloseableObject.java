package com.zhaoliang.java.lang;

import java.io.Closeable;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/24.
 */
public class CloseableObject implements Cloneable {

    private String hello;

    private Clone2Object clone2Object;

    public String getHello() {
        return hello;
    }

    @Override
    protected CloseableObject clone() throws CloneNotSupportedException {
        CloseableObject closeableObject = null;
        CloseableObject clone = (CloseableObject)super.clone();
        return closeableObject;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public Clone2Object getClone2Object() {
        return clone2Object;
    }

    public void setClone2Object(Clone2Object clone2Object) {
        this.clone2Object = clone2Object;
    }

    @Override
    public String toString() {
        return "CloseableObject{" +
            "hello='" + hello + '\'' +
            ", clone2Object=" + clone2Object +
            '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println(CloseableObject.class.getName());
        CloseableObject object = new CloseableObject();
        object.setHello("test");

        Clone2Object clone2Object = new Clone2Object();
        clone2Object.setHello(3251345);

        object.setClone2Object(clone2Object);

        CloseableObject clone = object.clone();

        System.out.println(clone.toString());
        System.out.println(object.equals(clone));
        System.out.println(object.getClone2Object().equals(clone.getClone2Object()));
        System.out.println(object);
        System.out.println(clone);
    }
}
