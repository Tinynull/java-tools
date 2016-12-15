package com.zhaoliang.pysample.ImplementingJavanterfaces;

import py4j.GatewayServer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/11/15.
 */
public class ListenerApplication {

    List<ExampleListener> listeners = new ArrayList<>();

    public void registerListener(ExampleListener listener) {
        listeners.add(listener);
    }

    public void notifyAllListeners() {
        for (ExampleListener listener : listeners) {
            Object returnValue = listener.notify(this);
            System.out.println(returnValue);
        }
    }

    @Override
    public String toString() {
        return "<ListenerApplication> instance";
    }

    public static void main(String[] args) {
        ListenerApplication application = new ListenerApplication();
        GatewayServer server = new GatewayServer(application);
        server.start(true);
    }
}
