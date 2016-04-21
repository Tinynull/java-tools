package com.zhaoliang.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

/**
 * A worker verticle is just like a standard verticle but it’s
 * executed not using an event loop, but using a thread from
 * the Vert.x worker thread pool.
 *
 * Worker verticles are designed for calling blocking code, as
 * they won’t block any event loops.
 *
 * If you don’t want to use a worker verticle to run blocking
 * code, you can also run inline blocking code directly while on
 * an event loop.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/21.
 */
public class WorkerVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions().setWorker(true);
        vertx.deployVerticle("com.company.MyOrderProcessorVerticle", options);
    }
}
