package com.zhaoliang.nio;

/**
 * 使用java的nio及非阻塞io.
 * Created by zhaoliang(weston_contribute@163.com) on 2016/8/10.
 */
public class TimeClient {

    public static void main(String[] args) {
        new Thread(new TimeClientHandle("127.0.0.1", 9999), "TimeClient-001").start();
    }
}
