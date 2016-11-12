package com.zhaoliang.javatuples;

import org.javatuples.Pair;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/11/10.
 */
public class Main {
    public static void main(String[] args) {
        Pair<String, String> pair = new Pair<>("name", "zhaoliang");
        System.out.println(pair.getValue0());
        System.out.println(pair.getValue1());
    }
}
