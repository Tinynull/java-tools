package com.zhaoliang.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/8/9.
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 9999;
        String ip = "127.0.0.1";
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("QUERY TIME ORDER");
            System.out.println("send order to server succeed.");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
            System.out.println("now is : " + response);
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (out != null) {
                out.close();
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                socket = null;
            }
        }

    }
}
