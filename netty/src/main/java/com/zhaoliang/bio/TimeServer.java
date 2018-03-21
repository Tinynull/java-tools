package com.zhaoliang.bio;

import com.zhaoliang.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/8/9.
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 9999;
        ServerSocket server;
        try {
            server = new ServerSocket(port);
            System.out.println("the time server is start in port : " + port);
            Socket socket;
            while (true) {
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
