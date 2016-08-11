package com.zhaoliang.aio;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/8/11.
 */
public class TimeClient {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 9999;

        new Thread(new AsyncTimeClientHandler(host, port), "AIO-AsyncTimeClientHandler-001").start();
    }
}
