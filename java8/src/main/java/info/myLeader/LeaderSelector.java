package info.myLeader;

import org.apache.commons.lang3.RandomUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zhaoliang
 * @create: 2018-05-18
 * @description:
 **/
public class LeaderSelector implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(LeaderSelector.class);
    private String path;
    private String zkAddr;

    public LeaderSelector(String path, String zkAddr) {
        this.path = path;
        this.zkAddr = zkAddr;
    }

    @Override
    public void run() {
        final LeaderLatch leaderLatch = new LeaderLatch(getClient(), path, "client#" + RandomUtils.nextInt(1, Integer.MAX_VALUE), LeaderLatch.CloseMode.NOTIFY_LEADER);
        leaderLatch.addListener(new LeaderLatchListener() {
            @Override
            public void isLeader() {
                Selection.setLeader(true);
                logger.info(leaderLatch.getId() + " is leader");
                System.out.println(leaderLatch.getId() + "is leader");
            }

            @Override
            public void notLeader() {
                Selection.setLeader(false);
                logger.info(leaderLatch.getId() + " is not leader");
                System.out.println(leaderLatch.getId() + "is not leader");
            }
        });
        try {
            leaderLatch.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private CuratorFramework getClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkAddr)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(6000)
                .namespace("vipCustomServiceLeader")
                .build();
        client.start();
        return client;
    }
}
