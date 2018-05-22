package info.myLeader;

/**
 * @author: zhaoliang
 * @create: 2018-05-18
 * @description:
 **/
public class Selection {
    private static boolean leader = false;

    public static boolean isLeader() {
        return leader;
    }

    public static void setLeader(boolean leader) {
        Selection.leader = leader;
    }
}
