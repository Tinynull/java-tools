package com.zhaoliang.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateClientExample {
	private static Logger logger = LoggerFactory.getLogger(CreateClientExample.class);
	private static final String PATH = "/example/basic/a";
	private static final String zookeeperAddress = "172.19.100.104:2181,172.19.100.104:2181,172.19.100.104:2181";

	public static void main(String[] args) throws Exception {
		CuratorFramework client = null;
		try {
			client = createSimple(zookeeperAddress);
			client.start();
			client.create().creatingParentsIfNeeded()
					.withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
					.forPath(PATH, "test".getBytes());
			Thread.sleep(30000L);
			logger.info("--------------------------------------");
			CloseableUtils.closeQuietly(client);
//
//			client = createWithOptions(zookeeperAddress,
//					new ExponentialBackoffRetry(1000, 3), 1000, 1000);
//			client.start();
//			System.out.println(new String(client.getData().forPath(PATH)));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			CloseableUtils.closeQuietly(client);
		}

	}

	public static CuratorFramework createSimple(String connectionString) {
		// these are reasonable arguments for the ExponentialBackoffRetry.
		// The first retry will wait 1 second - the second will wait up to 2
		// seconds - the
		// third will wait up to 4 seconds.
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000,
				3);
		// The simplest way to get a CuratorFramework instance. This will use
		// default values.
		// The only required arguments are the connection string and the retry
		// policy
		return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
	}

	public static CuratorFramework createWithOptions(String connectionString,
			RetryPolicy retryPolicy, int connectionTimeoutMs,
			int sessionTimeoutMs) {
		// using the CuratorFrameworkFactory.builder() gives fine grained
		// control
		// over creation options. See the CuratorFrameworkFactory.Builder
		// javadoc details
		return CuratorFrameworkFactory.builder()
				.connectString(connectionString).retryPolicy(retryPolicy)
				.connectionTimeoutMs(connectionTimeoutMs)
				.sessionTimeoutMs(sessionTimeoutMs)
				// etc. etc.
				.build();
	}
}