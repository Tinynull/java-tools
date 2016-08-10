package com.zhaoliang.nio;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/8/10.
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 9999;
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
