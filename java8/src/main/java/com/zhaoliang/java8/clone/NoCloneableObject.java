package com.zhaoliang.java8.clone;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/7/1.
 */
public class NoCloneableObject {

    public NoCloneableObject(String text) {
        this.text = text;
    }

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "NoCloneableObject{" +
            "text='" + text + '\'' +
            '}';
    }
}
