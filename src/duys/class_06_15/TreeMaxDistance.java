package duys.class_06_15;

/**
 * @ClassName TreeMaxDistance
 * @Author Duys
 * @Description 给定一个二叉树的头节点，任何两个节点之间存在距离，返回整颗二叉树的最大距离
 * @Date 2021/6/22 21:32
 **/
public class TreeMaxDistance {

    /**
     * 意思是：从节点A到节点B之间要经过的节点个数，每个要经过的节点只能算一次
     * 思路：x为头部的最大距离
     * 列出可能性：
     * 1.跟X有关
     * 1.1：左树的最大距离（高度）和右树的最大距离（高度）相加+ 1(x自己)
     * 2.跟X无关
     * 2.1：x的左树上最大距离
     * 2.2：x的右树上最大距离
     * 最终：三种可能性中求最大
     */
    public static class Info {
        public int dis;
        public int height;

        public Info(int d, int h) {
            dis = d;
            height = h;
        }
    }

    public static int max(Node head) {
        if (head == null) {
            return 0;
        }
        Info info = process(head);
        return Math.max(info.dis, info.height);
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        // 高度，左树高度和右树高度求最大，然后算上自己
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        // 三者中求最大的，因为就是找最大距离嘛
        int p1 = leftInfo.dis;
        int p2 = rightInfo.dis;
        int p3 = leftInfo.height + rightInfo.height + 1;
        int maxDis = Math.max(Math.max(p1, p2), p3);
        return new Info(maxDis, height);
    }
}
