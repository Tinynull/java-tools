package com.zhaoliang.java.lang;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/24.
 */
public class CloneableObject implements Cloneable {

    private String hello;

    private Clone2Object clone2Object;

    public String getHello() {
        return hello;
    }

    @Override
    protected CloneableObject clone() throws CloneNotSupportedException {
        CloneableObject closeableObject = null;
        CloneableObject clone = (CloneableObject)super.clone();
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


}
