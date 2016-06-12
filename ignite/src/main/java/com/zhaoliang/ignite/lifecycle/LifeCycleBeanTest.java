package com.zhaoliang.ignite.lifecycle;

import com.zhaoliang.ignite.MyLifecycleBean;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

/**
 * test.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/11.
 */
public class LifeCycleBeanTest {
    public static void main(String[] args) {

        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable client mode.
        cfg.setClientMode(true);

        cfg.setPeerClassLoadingEnabled(true);

        cfg.setLifecycleBeans(new MyLifecycleBean());

        // Start Ignite in client mode.
        Ignite ignite = Ignition.start(cfg);
    }
}
