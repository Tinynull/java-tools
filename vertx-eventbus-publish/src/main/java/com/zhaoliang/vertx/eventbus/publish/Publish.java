package com.zhaoliang.vertx.eventbus.publish;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.ext.web.Router;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

/**
 * publish.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/22.
 */
public class Publish extends AbstractVerticle {

    public static void main(String[] args) {
        HazelcastInstance instance = HazelcastClient.newHazelcastClient(new ClientConfig()
                                                                            .setGroupConfig(new GroupConfig("dev", "dev-pass"))
                                                                            .setNetworkConfig(new ClientNetworkConfig().addAddress("127.0.0.1")));
        ClusterManager mgr = new HazelcastClusterManager(instance);
        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                Vertx vertx = res.result();
                DeploymentOptions deploymentOptions = new DeploymentOptions();
                vertx.deployVerticle("com.zhaoliang.vertx.eventbus.publish.Publish", deploymentOptions);
            }
        });
    }


//    @Override
//    public void start() throws Exception {
//        EventBus eb = vertx.eventBus();
//        vertx.setPeriodic(5000, v -> {
//            eb.publish("ping-address", "ping!");
//            System.out.println("ping!");
//        });
//    }

    @Override
    public void start(Future<Void> fut) {
        Router router = Router.router(vertx);
        router.route("/:id").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            String id = routingContext.request().getParam("id");
            vertx.eventBus().publish("ping-address", id);
            response
                .putHeader("content-type", "text/html")
                .end("<h1>" + id + "</h1>");
        });

        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx
            .createHttpServer()
            .requestHandler(router::accept)
            .listen(8765, result -> {
                        if (result.succeeded()) {
                            fut.complete();
                        } else {
                            fut.fail(result.cause());
                        }
                    }
            );
    }
}
