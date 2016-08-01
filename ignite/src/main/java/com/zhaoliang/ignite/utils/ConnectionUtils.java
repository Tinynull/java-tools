package com.zhaoliang.ignite.utils;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

/**
 * Connection Utils.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/11.
 */
public class ConnectionUtils {

    public static Ignite getIgnite() {
        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable client mode.
        cfg.setClientMode(true);

        cfg.setPeerClassLoadingEnabled(true);

        // Start Ignite in client mode.
        return Ignition.start(cfg);
    }
}
