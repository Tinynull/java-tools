package com.zhaoliang.java8.clone;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/7/1.
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        CloneableObject cloneableObject = new CloneableObject(515);
        NoCloneableObject noCloneableObject = new NoCloneableObject("haha");
        MyObject myObject = new MyObject("weston", "wushiyouer", cloneableObject, noCloneableObject);
        MyObject myObject1 = myObject.clone();
        System.out.println("两个克隆对象相等么？：" + (myObject == myObject1));
        System.out.println(myObject);
        System.out.println(myObject1);
        System.out.println("两个属性cloneableObject相等么？：" + (myObject.getCloneableObject() == myObject1.getCloneableObject()));
        System.out.println("两个属性noCloneableObject相等么？：" + (myObject.getNoCloneableObject() == myObject1.getNoCloneableObject()));

        myObject.equals(myObject1);
    }
}
