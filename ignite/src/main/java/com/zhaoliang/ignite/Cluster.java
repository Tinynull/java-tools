package com.zhaoliang.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCluster;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterMetrics;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.configuration.IgniteConfiguration;

import java.util.Collection;

/**
 * Cluster
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/8.
 */
public class Cluster {
    public static void main(String[] args) {

        ////////////////////////
        ///////start client/////
        ////////////////////////
        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable client mode.
        cfg.setClientMode(true);

        // Start Ignite in client mode.
        Ignite ignite = Ignition.start(cfg);


        ////////////////////////
        /////获取集群的所有节点///
        ////////////////////////
        IgniteCluster cluster = ignite.cluster();
        Collection<String> strings = cluster.hostNames();
        System.out.println(strings);


        /////////////////////////////////
        /////获取本地节点的的metrics信息///
        /////////////////////////////////
        // Local Ignite node.
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
