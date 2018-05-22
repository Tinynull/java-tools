package info.leader;

/**
 * @author: zhaoliang
 * @create: 2018-05-18
 * @description:
 **/

import org.apache.commons.lang3.RandomUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 * Created by zhuzs on 2017/4/17.
 */
public class LeaderLatchTest {

    private static final String PATH = "/demo/leader";
    private static CuratorFramework client;

    public static void main(String[] args) {

        try {
            client = getClient();

            final LeaderLatch leaderLatch = new LeaderLatch(client, PATH, "client#" + RandomUtils.nextInt(1, 10000));
            leaderLatch.addListener(new LeaderLatchListener() {
                @Override
                public void isLeader() {
                    System.out.println(leaderLatch.getId() + ":I am leader. I am doing jobs!");
                }

                @Override
                public void notLeader() {
                    System.out.println(leaderLatch.getId() + ":I am not leader. I will do nothing!");
                }
            });
            leaderLatch.start();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static CuratorFramework getClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("172.16.68.161:2181,172.16.68.162:2181,172.16.68.163:2181")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .namespace("demo")
                .build();
        client.start();
        return client;
    }
}
