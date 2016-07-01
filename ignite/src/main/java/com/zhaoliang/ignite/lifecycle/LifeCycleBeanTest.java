package com.zhaoliang.ignite.lifecycle;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.configuration.IgniteConfiguration;

/**
 * test.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/11.
 */
public class LifeCycleBeanTest {
    public static void main(String[] args) throws InterruptedException {

        Ignite ignite = Ignition.start("D:\\github-weston\\java-tools\\ignite\\src\\main\\resources\\example-cache.xml");
        ClusterGroup clusterGroup = ignite.cluster().forAttribute("ROLE", "worker");
        clusterGroup.nodes().stream().forEach(clusterNode -> System.out.println(clusterNode.id()));

        IgniteCache<Object, Object> myccache = ignite.getOrCreateCache("myccache");
        myccache.put("hello","world");

        Thread.sleep(5000L);

        ignite.close();

    }
}
