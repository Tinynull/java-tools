package com.zhaoliang.curator.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ZKTreeListenr {

    private static Logger logger = LoggerFactory.getLogger(ZKListener.class);
    private String connectionString;
    public CuratorFramework client;
    private String listenPath;
    TreeCache cache;
    private Random random = new Random();

    private Map<String, String> zkValues = Collections.synchronizedMap(new HashMap<String, String>());

    public ZKTreeListenr(String connectionString, String path) {
        this.connectionString = connectionString;
        this.listenPath = path;
        client = createCuratorClient(this.connectionString);
        client.start();

        cache = new TreeCache(client, path);
        try {
            cache.start();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        addListener(cache);

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

    private void addListener(TreeCache cache) {
        TreeCacheListener listener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                updateValues();
            }
        };
        cache.getListenable().addListener(listener);
    }

    private synchronized void updateValues() {
        zkValues.clear();
        Map<String, ChildData> currentChildren = cache.getCurrentChildren(listenPath);
        for (String name : currentChildren.keySet()) {
            zkValues.put(name, String.valueOf(currentChildren.get(name).getData()));
        }
        logger.info("节点{}的子节点有改变，已更新，结果为：{}", listenPath, zkValues);
    }

    public String getRandomValue() {
        if (zkValues.size() == 0) {
            return "";
        } else {
            return zkValues.get(random.nextInt(zkValues.size()));
        }
    }

    public Map<String, String> getList() {
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
