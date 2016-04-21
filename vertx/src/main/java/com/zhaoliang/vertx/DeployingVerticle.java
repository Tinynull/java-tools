package com.zhaoliang.vertx;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/21.
 */
public class DeployingVerticle {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        Verticle myVerticle = new MyVerticle();
        vertx.deployVerticle(myVerticle);

        vertx.deployVerticle("com.mycompany.MyOrderProcessorVerticle");

        // Deploy a JavaScript verticle
        vertx.deployVerticle("verticles/myverticle.js");

        // Deploy a Ruby verticle verticle
        vertx.deployVerticle("verticles/my_verticle.rb");


        vertx.deployVerticle("com.mycompany.MyOrderProcessorVerticle", res -> {
            if (res.succeeded()) {
                System.out.println("Deployment id is: " + res.result());
            } else {
                System.out.println("Deployment failed!");
            }
        });



        vertx.undeploy("deploymentID", res -> {
            if (res.succeeded()) {
                System.out.println("Undeployed ok");
            } else {
                System.out.println("Undeploy failed!");
            }
        });


        /**
         * When deploying a verticle using a verticle name, you can
         * specify the number of verticle instances that you want to deploy:
         */
        DeploymentOptions options = new DeploymentOptions().setInstances(16);
        vertx.deployVerticle("com.mycompany.MyOrderProcessorVerticle", options);

        configVertx(vertx);


    }

    private static void configVertx(Vertx vertx) {
        /**
         * Configuration in the form of JSON can be passed to a verticle at deployment time:
         */
        JsonObject config = new JsonObject().put("name", "tim").put("directory", "/blah");
        DeploymentOptions options2 = new DeploymentOptions().setConfig(config);
        vertx.deployVerticle("com.mycompany.MyOrderProcessorVerticle", options2);

        System.out.println("Configuration: " + vertx.getOrCreateContext().config().getString("name"));
    }
}
