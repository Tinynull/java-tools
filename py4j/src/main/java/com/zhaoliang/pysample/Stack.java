package com.zhaoliang.pysample;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/11/15.
 */
public class Stack {
    private List<String> internalList = new LinkedList<String>();

    public void push(String element) {
        internalList.add(0, element);
    }

    public String pop() {
        return internalList.remove(0);
    }

    public List<String> getInternalList() {
        return internalList;
    }

    public void pushAll(List<String> elements) {
        for (String element : elements) {
            this.push(element);
        }
    }
}
