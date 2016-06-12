package com.zhaoliang.ignite.datagrid;

import static org.apache.ignite.cache.CacheAtomicityMode.TRANSACTIONAL;

import com.monogram.ignite.Person;
import com.zhaoliang.ignite.utils.ConnectionUtils;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheEntryProcessor;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.apache.ignite.lang.IgniteFuture;
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
        putAndWriteData(false);
        entryProcessorTest(false);
        atomicOperation(false);
        transactionsTest(false);
        lockTest(false);
        asynDataGridTest(false);
        queryCursorTest(true);
    }

    private static void queryCursorTest(boolean isTest) {
        if (!isTest) {
            return;
        }

        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);
        Ignite ignite = Ignition.start(cfg);

        IgniteCache<Integer, Person> cache = ignite.cache("person");
//        Iterator<Cache.Entry<Integer, Person>> iterator = cache.iterator();
//        while (iterator.hasNext()){
//            Cache.Entry<Integer, Person> next = iterator.next();
//            System.out.println(next.getKey() + " " + next.getValue().toString());
//        }

        IgniteBiPredicate<Integer, Person> filter = (IgniteBiPredicate<Integer, Person>) (integer, person) ->
            integer > 100;

        QueryCursor cursor = cache.query(new ScanQuery(filter));

        System.out.println(cursor.getAll());
        for (Object p111 : cursor) {
            Person p1 = (Person) p111;
            System.out.println(p1.getAddresses());
        }
        ignite.close();
    }

    /**
     * 下面是一个数据网格中异步调用的例子：
     *
     * @param isTest test.
     */
    private static void asynDataGridTest(boolean isTest) {
        if (!isTest) {
            return;
        }
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);
        Ignite ignite = Ignition.start(cfg);

        // Enable asynchronous mode.
        IgniteCache<Object, Object> asyncCache = ignite.cache("zhaoliangCache").withAsync();

        // Asynchronously store value in cache.
        asyncCache.getAndPut("1", 2);

        // Get future for the above invocation.
        IgniteFuture<Integer> fut = asyncCache.future();

        // Asynchronously listen for the operation to complete.
        fut.listen(f -> System.out.println("Previous cache value: " + f.get()));
    }

    /**
     * 我们再来一个小例子，它从/往分布式缓存中获取/添加数据，并
     * 且执行基本的事务。因为在应用中使用了缓存，要确保他是经过
     * 配置的，我们可以用Ignite自带的示例配置，他已经做了一些缓
     * 存的配置。
     */
    private static void putAndWriteData(boolean isTest) {
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

    /**
     * 也可以动态地创建缓存的一个实例，这时，Ignite会在所有的集群成员中创建和部署该缓存。
     */
    public static void dynamicCache() {
        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable client mode.
        cfg.setClientMode(true);

        cfg.setPeerClassLoadingEnabled(true);

        // Start Ignite in client mode.
        Ignite ignite = Ignition.start(cfg);

        CacheConfiguration cfg1 = new CacheConfiguration();
        cfg1.setName("myCache");
        cfg1.setAtomicityMode(TRANSACTIONAL);

        // Create cache with given name, if it does not exist.
        IgniteCache<Integer, String> cache = ignite.getOrCreateCache(cfg1);
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


    public static void entryProcessorTest(boolean isTest) {
        if (!isTest) {
            return;
        }
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

    /**
     * Exception in thread "main"
     * javax.cache.CacheException: Locks are not supported for
     * CacheAtomicityMode.ATOMIC mode (use CacheAtomicityMode.TRANSACTIONAL instead)
     *
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
        if (!isTest) {
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
