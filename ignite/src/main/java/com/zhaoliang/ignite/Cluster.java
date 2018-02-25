package com.zhaoliang.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCluster;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterMetrics;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Cluster
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/8.
 */
public class Cluster {
    public static void main(String[] args) {

        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Collections.singletonList("127.0.0.1"));
//        ipFinder.setAddresses(Collections.singletonList("47.92.101.233"));
        spi.setIpFinder(ipFinder);
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setDiscoverySpi(spi);

        // Enable client mode.
        cfg.setClientMode(true);

        cfg.setPeerClassLoadingEnabled(false);


        // Start Ignite in client mode.
        Ignite ignite = Ignition.start(cfg);


        // 获取集群的所有节点
        IgniteCluster cluster = ignite.cluster();
        Collection<String> strings = cluster.hostNames();
        System.out.println("cluster nodes : " + strings);

        // 获取本地节点的的metrics信息
        ClusterNode localNode = cluster.localNode();
        System.out.println("localNode().id()=" + localNode.id());

        // Node metrics.
        ClusterMetrics metrics = localNode.metrics();
        // Get some metric values.
        double cpuLoad = metrics.getCurrentCpuLoad();
        long usedHeap = metrics.getHeapMemoryUsed();
        int numberOfCores = metrics.getTotalCpus();
        int activeJobs = metrics.getCurrentActiveJobs();
        System.out.println("----------------------------------------");
        System.out.println("cpuLoad=" + cpuLoad);
        System.out.println("usedHeap=" + usedHeap);
        System.out.println("numberOfCores=" + numberOfCores);
        System.out.println("activeJobs=" + activeJobs);


        /**
         * IgniteCluster接口也是一个集群组，只不过包括集群内的所有节点。
         * 可以限制作业执行、服务部署、消息、事件以及其他任务只在部分集群
         * 组内执行，比如，下面这个例子只把作业广播到远程节点（除了本地节点）：
         */
//        IgniteCompute compute = ignite.compute(cluster.forRemotes());
        // Broadcast to all remote nodes and print the ID of the node
        // on which this closure is executing.
//        compute.broadcast(() -> System.out.println("Hello Node: " + cluster.localNode().id()));
//        compute.broadcast(() -> System.out.println("Hello Node: "));
    }
}
