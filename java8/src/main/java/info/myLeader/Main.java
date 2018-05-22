package info.myLeader;

/**
 * @author: zhaoliang
 * @create: 2018-05-18
 * @description:
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        LeaderSelector leaderSelector = new LeaderSelector("/vipService/leader", "127.0.0.1:2181");
        new Thread(leaderSelector).start();
        Thread.sleep(Long.MAX_VALUE);
    }
}
