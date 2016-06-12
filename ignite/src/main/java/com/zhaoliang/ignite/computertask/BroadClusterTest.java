package com.zhaoliang.ignite.computertask;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

/**
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/8.
 */
public class BroadClusterTest {
    public static void main(String[] args) {

        ////////////////////////
        ///////start client/////
        ////////////////////////
        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable client mode.
        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);

        // Start Ignite in client mode.
        Ignite ignite = Ignition.start(cfg);

        // Limit broadcast to remote nodes only.
        IgniteCompute compute = ignite.compute();
        // Print out hello message on remote nodes in the cluster group.
        compute.broadcast(() -> System.out.println("Hello Node: " + ignite.cluster().localNode().hostNames()));
        ignite.close();
    }
}
