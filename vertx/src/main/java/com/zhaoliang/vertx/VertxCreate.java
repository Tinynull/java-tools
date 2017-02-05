package com.zhaoliang.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * learn how to create vertx object.
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/21.
 */
public class VertxCreate {
    public static void main(String[] args) {

        /*
         * Most applications will only need a single Vert.x instance,
         * but it’s possible to create multiple Vert.x instances if
         * you require, for example, isolation between the event bus
         * or different groups of servers and clients.
         */
        Vertx vertx1 = Vertx.vertx();

        /*
         * The VertxOptions object has many settings and allows you to
         * configure things like clustering, high availability, pool
         * sizes and various other settings. The Javadoc describes all
         * the settings in detail.
         */
        Vertx vertx2 = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));


        /*
         * For example to receive a timer event every second you would do:
         */
        vertx1.setPeriodic(1000, id -> {
            // This handler will get called every second
            System.out.println("timer fired!");
        });
    }
}
