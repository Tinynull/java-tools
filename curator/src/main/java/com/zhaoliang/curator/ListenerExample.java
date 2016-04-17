package com.zhaoliang.curator;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import static com.zhaoliang.curator.study.Constants.*;

public class ListenerExample {

	public static String PATH = "/listen/test";

	public static void main(String[] args) throws Exception {
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000,
				3);
		CuratorFramework client = CuratorFrameworkFactory.newClient(
				zookeeperAddress, retryPolicy);

		client.start();

		client.create().creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
				.forPath(PATH + "/a", "test".getBytes());

		List<String> forPath = client.getChildren().usingWatcher(new Watcher() {

			@Override
			public void process(WatchedEvent event) {
				System.out.println(event.getPath() + " " + event.getType());

			}
		}).forPath(PATH);

		for (int i = 0; i < 10000; i++) {
			Thread.sleep(2000L);
			client.create().creatingParentsIfNeeded()
					.withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
					.forPath(PATH + "/a", "test".getBytes());
			System.out.println(forPath);
		}
		client.close();
	}

}
