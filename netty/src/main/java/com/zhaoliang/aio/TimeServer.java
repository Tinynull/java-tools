package com.zhaoliang.aio;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/8/11.
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 9999;

        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer,"AIO-AsyncTimeServerHandler-001").start();
    }
}
