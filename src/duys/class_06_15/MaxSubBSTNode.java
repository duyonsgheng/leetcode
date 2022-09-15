package duys.class_06_15;

/**
 * @ClassName MaxSubBSTNode
 * @Author Duys
 * @Description 返回二叉树最大二叉搜索子树的头节点
 * @Date 2021/6/29 15:40
 **/
public class MaxSubBSTNode {
    /**
     * 题目：给定一棵二叉树的头节点head，
     * 返回这颗二叉树中最大的二叉搜索子树的头节点
     * 和返回最大二叉搜索子树节点数类似，只是返回是头节点
     */
    /**
     * 可能性分析：
     * 1.与x有关
     *  x就是头节点
     * 2.与x无关
     * 1.头节点在左树上
     * 2.头节点在右树上
     */
    /**
     * 信息收集
     * 1.左树上要最大值
     * 2.左树上要最小值
     * 3.左树上头节点
     * 4.左树上最大二叉搜索树的所有节点数
     */

    public static class Info {
        public int maxBSTSize;
        public int max;
        public int min;
        public Node head;

        public Info(int all, int mx, int mi, Node h) {
            maxBSTSize = all;
            max = mx;
            min = mi;
            head = h;
        }
    }

    public static Node maxSubHeadNode(Node head) {
        return process(head).head;
    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int maxBSTSize = 0;
        Node head = null;
        int max = x.value;
        int min = x.value;
        // x无关的情况
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            maxBSTSize = leftInfo.maxBSTSize;
            head = leftInfo.head;
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            // 这里如果右边的最大二叉树节点个数已经是比当前存在的大了，才会取右边的，否则只计算左边的
            if (rightInfo.maxBSTSize > maxBSTSize) {
                maxBSTSize = rightInfo.maxBSTSize;
                head = rightInfo.head;
            }
        }
        // 也就是与X有关的时候了
        // 这里就是整棵树链接起来的时候进行判断，x就是整个最大二叉搜索树的头
        if (leftInfo == null ? true : (leftInfo.head == x.left && leftInfo.max < x.value)
                && rightInfo == null ? true : (rightInfo.head == x.right && rightInfo.max > x.value)) {
            head = x;
            maxBSTSize = (leftInfo == null ? 0 : leftInfo.maxBSTSize) +
                    (rightInfo == null ? 0 : rightInfo.maxBSTSize) + 1;
        }
        return new Info(maxBSTSize, max, min, head);
    }
}
