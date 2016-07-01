package com.zhaoliang.java.lang;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/7/1.
 */
public class NoCloneableObject {
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
