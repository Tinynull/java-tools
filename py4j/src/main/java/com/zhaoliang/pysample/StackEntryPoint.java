package com.zhaoliang.pysample;

import py4j.GatewayServer;

/**
 * 启动此类后，在python解释器中运行一下命令：
 * from py4j.java_gateway import JavaGateway
 * gateway = JavaGateway()
 * stack = gateway.entry_point.getStack()
 * stack.push("First %s" % ('item'))
 * stack.push("Second item")
 * stack.pop()
 * stack.pop()
 * <p>
 * Created by zhaoliang (weston_contribute@163.com) on 2016/11/15.
 */
public class StackEntryPoint {
    private Stack stack;

    public StackEntryPoint() {
        stack = new Stack();
        stack.push("Initial Item");
    }

    public Stack getStack() {
        return stack;
    }

    public static void main(String[] args) {
        GatewayServer gatewayServer = new GatewayServer(new StackEntryPoint());
        gatewayServer.start();
        System.out.println("Gateway Server Started");
    }
}
