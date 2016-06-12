package com.zhaoliang.ignite.datagrid;

import static org.apache.ignite.cache.CacheAtomicityMode.TRANSACTIONAL;

import com.zhaoliang.ignite.utils.ConnectionUtils;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.configuration.CacheConfiguration;

/**
 * CacheConfiguration.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/8.
 */
public class Test1 {
    public static void main(String[] args) {

        // Start Ignite in client mode.
        Ignite ignite = ConnectionUtils.getIgnite();

        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setName("myCache");
        cacheConfiguration.setAtomicityMode(TRANSACTIONAL);
        // Create cache with given name, if it does not exist.
        IgniteCache<Integer, String> cache = ignite.getOrCreateCache(cacheConfiguration);
//        cache.put(1,"value1");
        for (int i = 0; i < 10; i++)
            cache.put(i, Integer.toString(i));

        ignite.close();

    }
}
