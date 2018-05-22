package info.zookeeper;

/**
 * @author: zhaoliang
 * @create: 2018-05-18
 * @description:
 **/

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;

public class Node1 implements Watcher {
    ZooKeeper zk;
    String hostPort;
    String znode;

    public Node1(String hostPort, String znode) throws Throwable {
        this.hostPort = hostPort;
        this.znode = znode;

        zk = new ZooKeeper(hostPort, 3000, this);
        try {
            //每个客户端都创建同一个节点，如果创建成功，则该客户端是master
            zk.create(znode, "node1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("master节点是：node1");
        } catch (KeeperException.NodeExistsException e) {
            //如果抛出节点存在的异常，则master已经存在，在该节点上添加watcher
            System.out.println("master节点是：" + new String(zk.getData(znode, false, null)));
            zk.exists(znode, true);
        }

    }

    public static void main(String[] args) throws Throwable {
        new Node1("127.0.0.1:2181", "/master");

        System.in.read();
    }

    @Override
    public void process(WatchedEvent event) {
        try {

            if (event.getType() == EventType.NodeDeleted) {
                try {
                    zk.create(znode, "node1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    System.out.println("master节点是：node1");
                } catch (KeeperException.NodeExistsException e) {
                    System.out.println("master节点是：" + new String(zk.getData(znode, false, null)));
                    zk.exists(znode, true);
                }
            }

        } catch (KeeperException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

