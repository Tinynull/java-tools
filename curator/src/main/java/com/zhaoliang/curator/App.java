package com.zhaoliang.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 *
 */
public class App {
	
	private static final String zookeeperAddress = "172.19.100.104:2181,172.19.100.104:2181,172.19.100.104:2181";
	public static void main(String[] args) throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperAddress, retryPolicy);
		client.start();
		Stat forPath = client.checkExists().forPath("/my");
		if(forPath == null){
			client.create().forPath("/my", "zl".getBytes());
		}
		byte[] forPath2 = client.getData().watched().inBackground().forPath("/my");
		System.out.println(new String(forPath2));
		
	}
}
