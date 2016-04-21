package com.zhaoliang.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/21.
 */
public class MyVerticle extends AbstractVerticle {

    public void start(Future<Void> startFuture) {
        // Now deploy some other verticle:

        vertx.deployVerticle("com.foo.OtherVerticle", res -> {
            if (res.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail("fail");
            }
        });
    }


    public void stop(Future<Void> stopFuture) {
      /*  obj.doSomethingThatTakesTime(res -> {
            if (res.succeeded()) {
                stopFuture.complete();
            } else {
                stopFuture.fail();
            }
        });*/
    }
}
