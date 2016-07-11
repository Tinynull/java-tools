package com.zhaoliang.extend;

/**
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/7/4.
 */
public interface SuperClass1 {
    default String hello() {
        return "SuperClass1";
    }
}
