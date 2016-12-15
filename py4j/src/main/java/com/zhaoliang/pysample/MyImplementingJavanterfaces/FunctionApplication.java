package com.zhaoliang.pysample.MyImplementingJavanterfaces;

import com.zhaoliang.pysample.ImplementingJavanterfaces.ExampleListener;
import py4j.GatewayServer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/11/15.
 */
public class FunctionApplication {

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
        FunctionApplication application = new FunctionApplication();
        GatewayServer server = new GatewayServer(application);
        server.start(true);
    }
}
