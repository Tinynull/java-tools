package com.zhaoliang.vertx.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/25.
 */
public class RestService extends AbstractVerticle {

    public static void main(String[] args) {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new RestService());
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Router router = Router.router(vertx);
        router.get("/api/:id").handler(this::test);
        vertx.createHttpServer().requestHandler(router::accept).listen(8888);
    }

    private void test(RoutingContext routingContext){
        final HttpServerRequest request = routingContext.request();
        final JsonObject jsonObject = new JsonObject();
        final String id = request.getParam("id");
        jsonObject.put("absoluteURI",request.absoluteURI());
        jsonObject.put("method",request.method().toString());
        jsonObject.put("path",request.path());
        jsonObject.put("uri",request.uri());
        jsonObject.put("version",request.version().toString());
        jsonObject.put("id",id);
        routingContext.response().putHeader("content-Type","application/json").end(jsonObject.toString());
    }


}
