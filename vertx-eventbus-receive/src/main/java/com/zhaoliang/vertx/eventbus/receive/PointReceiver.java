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
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/25.
 */
public class PointReceiver extends AbstractVerticle {

    public static void main(String[] args) {
        HazelcastInstance instance = HazelcastClient.newHazelcastClient(new ClientConfig()
                                                                            .setGroupConfig(new GroupConfig("dev", "dev-pass"))
                                                                            .setNetworkConfig(new ClientNetworkConfig().addAddress("127.0.0.1")));
        ClusterManager mgr = new HazelcastClusterManager(instance);
        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                Vertx vertx = res.result();
                vertx.deployVerticle(new PointReceiver());
            }
        });
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        if(startFuture.succeeded()){
            System.out.println(this.getClass().getName() + " is deployed successful !");
        }

        vertx.eventBus().consumer("point",event -> {
            System.out.println("recevied : " + event.body().toString());
            event.reply("world");
        });
    }
}
