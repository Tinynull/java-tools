package com.zhaoliang.ignite.IgniteAsyncSupport;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteFuture;

/**
 * IgniteAsyncSupport Test
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/12.
 */
public class IgniteAsyncSupportTest {
    public static void main(String[] args) {

        syncTest(false);
        asynSupport(false);
        asynDataGridTest(true);
    }


    private static void syncTest(boolean isTest) {
        if (!isTest) {
            return;
        }
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);
        Ignite ignite = Ignition.start(cfg);

        IgniteCompute compute = ignite.compute();
        // Execute a job and wait for the result.
        String res = compute.call(() -> {
            // Print hello world on some cluster node.
            System.out.println("Hello World");
            return "Hello World";
        });
    }

    /**
     * IgniteAsyncSupport接口为很多Ignite API提供了异步模型，比如，IgniteCompute,
     * IgniteServices,IgniteCache以及IgniteTransactions,都实现了IgniteAsyncSupport接口。
     * 要启用异步模式，需要调用withAsync()方法，他会返回同样API的实例，这样的话，异步功能就启用了。
     *
     * @param isTest 是否测试。
     */
    private static void asynSupport(boolean isTest) {
        if (!isTest) {
            return;
        }
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);
        Ignite ignite = Ignition.start(cfg);

        // Enable asynchronous mode.
        IgniteCompute asyncCompute = ignite.compute().withAsync();
        // Asynchronously execute a job.
        asyncCompute.call(() -> {
            // Print hello world on some cluster node and wait for completion.
            System.out.println("Hello World");
            Thread.sleep(2000L);
            return "Hello World";
        });
        // Get the future for the above invocation.
        IgniteFuture<String> fut = asyncCompute.future();
        // Asynchronously listen for completion and print out the result.
        fut.listen(f -> System.out.println("Job result: " + f.get()));
    }

    /**
     * 下面是一个数据网格中异步调用的例子：
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
}
