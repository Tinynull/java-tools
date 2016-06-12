package com.zhaoliang.ignite.datagrid;

import com.zhaoliang.ignite.utils.ConnectionUtils;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.transactions.Transaction;

import java.util.concurrent.locks.Lock;

/**
 * 我们再来一个小例子，它从/往分布式缓存中获取/添加数据，并
 * 且执行基本的事务。因为在应用中使用了缓存，要确保他是经过
 * 配置的，我们可以用Ignite自带的示例配置，他已经做了一些缓
 * 存的配置。
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/7.
 */
public class DataGridTest {
    public static void main(String[] args) {
        test1(false);
        atomicOperation(false);
        transactionsTest(false);
        lockTest(true);
    }

    /**
     * 我们再来一个小例子，它从/往分布式缓存中获取/添加数据，并
     * 且执行基本的事务。因为在应用中使用了缓存，要确保他是经过
     * 配置的，我们可以用Ignite自带的示例配置，他已经做了一些缓
     * 存的配置。
     */
    private static void test1(boolean isTest) {
        if (!isTest) {
            return;
        }
        try (Ignite ignite = Ignition.start("D:\\ignite-1.6.0\\examples\\config\\example-ignite.xml")) {
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

    public static void atomicOperation(boolean isTest) {
        if (!isTest) {
            return;
        }


        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable client mode.
        cfg.setClientMode(true);

        cfg.setPeerClassLoadingEnabled(true);

        // Start Ignite in client mode.
        Ignite ignite = Ignition.start(cfg);

        IgniteCache<String, Integer> cache = ignite.getOrCreateCache("zhaoliangCache");

        // Put-if-absent which returns previous value.
        Integer oldVal = cache.getAndPutIfAbsent("Hello", 11);

        // Put-if-absent which returns boolean success flag.
        boolean success = cache.putIfAbsent("World", 22);

        // Replace-if-exists operation (opposite of getAndPutIfAbsent), returns previous value.
        oldVal = cache.getAndReplace("Hello", 11);

        // Replace-if-exists operation (opposite of putIfAbsent), returns boolean success flag.
        success = cache.replace("World", 22);

        // Replace-if-matches operation.
        success = cache.replace("World", 2, 22);

        // Remove-if-matches operation.
        success = cache.remove("Hello", 1);
    }

    /**
     * Exception in thread "main"
     * javax.cache.CacheException: Locks are not supported for
     * CacheAtomicityMode.ATOMIC mode (use CacheAtomicityMode.TRANSACTIONAL instead)
     * @param isTest
     */
    private static void transactionsTest(boolean isTest) {
        if (!isTest) {
            return;
        }

        Ignite ignite = ConnectionUtils.getIgnite();
        IgniteCache<String, Integer> cache = ignite.getOrCreateCache("zhaoliangCache");
        try (Transaction tx = ignite.transactions().txStart()) {
            Integer hello = cache.get("Hello");
            if (hello == 1) {
                cache.put("Hello", 11);
            }
            cache.put("World", 22);
            tx.commit();
        }
        ignite.close();
    }

    private static void lockTest(boolean isTest) {
        if(!isTest){
            return;
        }

        Ignite ignite = ConnectionUtils.getIgnite();
        IgniteCache<String, Integer> cache = ignite.getOrCreateCache("zhaoliangCache");

        // Lock cache key "Hello".
        Lock lock = cache.lock("Hello");
        lock.lock();
        try {
            cache.put("Hello", 33);
            cache.put("World", 44);
        } finally {
            lock.unlock();
        }
    }
}
