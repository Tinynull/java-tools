package com.zhaoliang.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

/**
 * 连接ignite集群。
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/7.
 */
public class Client {
    public static void main(String[] args) {
        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable client mode.
        cfg.setClientMode(true);

        // Start Ignite in client mode.
        Ignite ignite = Ignition.start(cfg);

        IgniteCache<Integer, String> cache = ignite.getOrCreateCache("myCacheName");

        // Store keys in cache (values will end up on different cache nodes).
        for (int i = 0; i < 10; i++) {
            cache.put(i, Integer.toString(i));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("Got [key=" + i + ", val=" + cache.get(i) + ']');
        }
    }
}
