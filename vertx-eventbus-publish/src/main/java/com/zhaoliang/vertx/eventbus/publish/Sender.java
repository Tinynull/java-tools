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
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

/**
 * point to point.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/25.
 */
public class Sender extends AbstractVerticle {

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
                vertx.deployVerticle("com.zhaoliang.vertx.eventbus.publish.Sender", deploymentOptions);
            }
        });
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        System.out.println("send a message :" + " hello");
        vertx.eventBus().send("point", "hello", event -> {
            if (event.succeeded()) {
                System.out.println("replied message : " + event.result().body());
            } else {
                System.out.println("failure!");
            }
        });
    }
}
