package com.zhaoliang.curator.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ZKListener {
    private static Logger logger = LoggerFactory.getLogger(ZKListener.class);
    private String connectionString;
    public CuratorFramework client;
    private String listenPath;
    PathChildrenCache cache;
    private Random random = new Random();

    private List<String> zkValues = Collections.synchronizedList(new ArrayList<String>());

    public ZKListener(String connectionString, String path) {
        this.connectionString = connectionString;
        this.listenPath = path;
        client = createCuratorClient(this.connectionString);
        client.start();

        cache = new PathChildrenCache(client, path, true);
        try {
            cache.start();
            
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        addListener(cache);
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     * @param connectionString
     * @return
     */
    private CuratorFramework createCuratorClient(String connectionString) {
        /*
         * 这是一个重试的链接策略，为经验值。第一次重试等待一秒，第二次重试等待2秒，第三次重试等待4秒。
         */
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
    }

    private void addListener(PathChildrenCache cache) {
        PathChildrenCacheListener listener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                updateValues();
            }
        };
        cache.getListenable().addListener(listener);
    }

    private void updateValues() {
        zkValues.clear();
        for (ChildData data : cache.getCurrentData()) {
            zkValues.add(new String(data.getData()));
        }
        logger.info("节点{}的子节点有改变，已更新，结果为：{}", listenPath, zkValues);
    }

    public String getRandomValue() {
        
//        States states = null;
//        try {
//            states = client.getZookeeperClient().getZooKeeper().getState();
//            System.out.println(states);
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//
//        if (!States.CONNECTED.equals(states)) {
//
//            while (!States.CONNECTED.equals(states)) {
//                try {
//                    Thread.sleep(50L);
//                } catch (Exception e) {
//                }
//
//                try {
//                    states = client.getZookeeperClient().getZooKeeper().getState();
//                    System.out.println(states);
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                }
//            }
//
//            try {
//                Thread.sleep(2000L);
//            } catch (Exception e) {
//            }
//
//        }

        if (zkValues.size() == 0) {
            return "";
        } else {
            return zkValues.get(random.nextInt(zkValues.size()));
        }
    }

    public List<String> getList() {
        return zkValues;
    }

    /**
     * 
     */
    public void closeClient() {
        if (client != null) {
            CloseableUtils.closeQuietly(client);
        }
        if (cache != null) {
            CloseableUtils.closeQuietly(cache);
        }
    }

}
