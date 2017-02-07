package com.zhaoliang.vertx.blocking;

import java.util.Random;

import io.vertx.core.Vertx;
import io.vertx.core.AbstractVerticle;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class ExecBlockingExample extends AbstractVerticle {

    private Random random = new Random();

    private boolean aBoolean = true;

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new ExecBlockingExample());
    }

    @Override
    public void start() throws Exception {

        vertx.createHttpServer().requestHandler(request -> {

            // Let's say we have to call a blocking API (e.g. JDBC) to execute a query for each
            // request. We can't do this directly or it will block the event loop
            // But you can do this using executeBlocking:

            vertx.<Integer>executeBlocking(future -> {

                // Do the blocking operation in here
                // Imagine this was a call to a blocking API to get the result
                if(aBoolean){
                    aBoolean = false;
                }else{
                    aBoolean = true;
                    try {
                        Thread.sleep(5000);
                    } catch (Exception ignore) {
                    }
                }
                int result = random.nextInt(40);
                future.complete(result);
            }, res -> {
                if (res.succeeded()) {
                    request.response().putHeader("content-type", "text/plain").end(String.valueOf(res.result() + 10));
                } else {
                    res.cause().printStackTrace();
                }
            });

        }).listen(8889);

    }
}
