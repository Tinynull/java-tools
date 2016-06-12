package com.zhaoliang.ignite.datagrid;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

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
        test1();
    }

    private static void test1() {
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
}
