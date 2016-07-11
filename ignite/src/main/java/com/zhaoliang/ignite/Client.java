package com.zhaoliang.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

import java.io.File;

/**
 * 连接ignite集群。
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/7.
 */
public class Client {
    public static void main(String[] args) {
        Ignite ignite = getIgnite1();

        // Gets existing cache with the given name or creates new one using template configuration.
        IgniteCache<Integer, String> cache = ignite.getOrCreateCache("myCacheName");

        // Store keys in cache (values will end up on different cache nodes).
        for (int i = 0; i < 10; i++) {
            cache.put(i, Integer.toString(i));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("Got [key=" + i + ", val=" + cache.get(i) + ']');
        }
    }

    /**
     * 通过默认方式加载。
     *
     * @return {@link Ignite}
     */
    public static Ignite getIgnite1() {
        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable client mode.
        cfg.setClientMode(true);

        // Start Ignite in client mode.
        return Ignition.start(cfg);
    }

    /**
     * 通过配置文件加载。直接加载example-default.xml失败。
     * 需要加载example-ignite.xml,在此文件中<import resource="example-default.xml"/> 方式引用。
     *
     * @return {@link Ignite}
     */
    public static Ignite getIgnite2() {
        String configFile = "D:\\github-weston\\java-tools\\ignite\\src\\main\\resources\\example-ignite.xml";
        Ignite ignite = Ignition.start(configFile);
        return ignite;
    }
}
