package com.zhaoliang.vertx.eventbus.receive;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.ext.web.Router;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

/**
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/22.
 */
public class Reveiver extends AbstractVerticle {

    public static void main(String[] args) {
        HazelcastInstance instance = HazelcastClient.newHazelcastClient(new ClientConfig()
                                                                            .setGroupConfig(new GroupConfig("dev", "dev-pass"))
                                                                            .setNetworkConfig(new ClientNetworkConfig().addAddress("127.0.0.1")));
        ClusterManager mgr = new HazelcastClusterManager(instance);
        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                Vertx vertx = res.result();
                vertx.deployVerticle(new Reveiver());
            }
        });
    }

    @Override
    public void start() throws Exception {
        EventBus eb = vertx.eventBus();
        eb.consumer("ping-address", message -> {
            System.out.println("Received message: " + message.body());
            message.reply("pong!");
        });
        System.out.println("Receiver ready!");
    }


}
