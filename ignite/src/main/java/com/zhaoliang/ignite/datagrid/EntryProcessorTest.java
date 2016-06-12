package com.zhaoliang.ignite.datagrid;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheEntryProcessor;
import org.apache.ignite.configuration.IgniteConfiguration;

/**
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/8.
 */
public class EntryProcessorTest {
    public static void main(String[] args) {
        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable client mode.
        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);

        // Start Ignite in client mode.
        Ignite ignite = Ignition.start(cfg);

        IgniteCache<String, Integer> cache = ignite.cache("myCache");

        // Increment cache value 10 times.
        for (int i = 0; i < 10; i++) {
            cache.invoke("mykey", (CacheEntryProcessor<String, Integer, Object>) (entry, args1) -> {
                Integer val = entry.getValue();

                entry.setValue(val == null ? 1 : val + 1);

                return null;
            });
        }

        ignite.close();
    }
}
