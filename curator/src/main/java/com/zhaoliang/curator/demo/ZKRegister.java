package com.zhaoliang.curator.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKRegister {

    private static Logger logger = LoggerFactory.getLogger(ZKRegister.class);
    private String connectionString;
    public CuratorFramework client;

    public ZKRegister(String connectionString) {
        this.connectionString = connectionString;
        client = createCuratorClient(this.connectionString);
        client.start();
        logger.info(connectionString + "已经连接。");
    }

    /**
     * 
     * @param connectionString
     * @return
     */
    private CuratorFramework createCuratorClient(String connectionString) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
    }

    /**
     * 
     * @param path
     * @param nodeName
     * @param nodeValue
     * @throws Exception
     */
    public void register(String path, String nodeName, String nodeValue) throws Exception {
        if (client == null) {
            client = createCuratorClient(connectionString);
            client.start();
        }
        if (client.checkExists().forPath(path) == null) {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path, path.getBytes());
        }
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
            .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path + "/" + nodeName, nodeValue.getBytes());
    }

    /**
     * 
     * @param connectionString
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    /**
     * 
     * @return
     */
    public CuratorFramework getClient() {
        return client;
    }

    /**
     * 
     */
    public void closeClient() {
        if (client != null) {
            CloseableUtils.closeQuietly(client);
        }
    }

}
