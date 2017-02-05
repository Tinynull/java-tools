package com.zhaoliang.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * Verticle.
 * <p>
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/21.
 */
public class VerticleTest extends AbstractVerticle {

    // Called when verticle is deployed
    public void start() {
        System.out.println("VerticleTest started");
    }

    // Optional - called when verticle is undeployed
    public void stop() {
        System.out.println("VerticleTest stopped");
    }

    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle("com.zhaoliang.vertx.VerticleTest");
        vertx.undeploy("com.zhaoliang.vertx.VerticleTest");
    }
}

