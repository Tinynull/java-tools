package com.zhaoliang.java8.clone;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/7/1.
 */
public class MyObject implements Cloneable {

    private String name;
    private String age;
    private CloneableObject cloneableObject;
    private NoCloneableObject noCloneableObject;

    public MyObject(String name, String age, CloneableObject cloneableObject, NoCloneableObject noCloneableObject) {
        this.name = name;
        this.age = age;
        this.cloneableObject = cloneableObject;
        this.noCloneableObject = noCloneableObject;
    }

    @Override
    public MyObject clone() throws CloneNotSupportedException {
        MyObject clone = (MyObject) super.clone();
        clone.setCloneableObject(cloneableObject.clone());
        return clone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public CloneableObject getCloneableObject() {
        return cloneableObject;
    }

    public void setCloneableObject(CloneableObject cloneableObject) {
        this.cloneableObject = cloneableObject;
    }

    public NoCloneableObject getNoCloneableObject() {
        return noCloneableObject;
    }

    public void setNoCloneableObject(NoCloneableObject noCloneableObject) {
        this.noCloneableObject = noCloneableObject;
    }

    @Override
    public String toString() {
        return "MyObject{" +
            "name='" + name + '\'' +
            ", age='" + age + '\'' +
            ", cloneableObject=" + cloneableObject +
            ", noCloneableObject=" + noCloneableObject +
            '}';
    }
}
