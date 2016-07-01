package com.zhaoliang.java.lang;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/7/1.
 */
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println(CloneableObject.class.getName());
        CloneableObject object = new CloneableObject();
        object.setHello("test");

        Clone2Object clone2Object = new Clone2Object();
        clone2Object.setHello(3251345);

        object.setClone2Object(clone2Object);

        CloneableObject clone = object.clone();

        System.out.println(clone.toString());
        System.out.println(object.equals(clone));
        System.out.println(object.getClone2Object().equals(clone.getClone2Object()));
        System.out.println(object);
        System.out.println(clone);
    }
}
