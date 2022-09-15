package duys_code.day_14;

/**
 * @ClassName Code_03_BSTNodes
 * @Author Duys
 * @Description
 * @Date 2021/10/25 13:13
 **/
public class Code_03_BSTNodes {
    /**
     * 给定一个棵完全二叉树，
     * 返回这棵树的节点个数，
     * 要求时间复杂度小于O(树的节点数)
     */
    /**
     * 完全二叉树：从head出发。
     * 如果我左树的高度 = 树的高度了。那么我的右树一定是满的
     * 如果我的右树高度 = 树的高度了。那么我的左树一定是满的
     */

    public static class Node {
        public Node left;
        public Node right;
        public int value;

        public Node(int data) {
            value = data;
        }
    }

    public static int numNode(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    // 当前来到的树的头节点。处在第几层。整棵树高度
    public static int bs(Node head, int level, int h) {
        if (level == h) {
            return 1;
        }
        // 我的右树高度
        if (mostLeftLevel(head.right, level + 1) == h) {
            return (1 << (h - level)) + bs(head.right, level + 1, h);
        } else {
            return (1 << (h - level - 1)) + bs(head.left, level + 1, h);
        }
    }

    public static int mostLeftLevel(Node head, int level) {
        while (head != null) {
            level++;
            head = head.left;
        }
        // 最后为空的时候多算了一次。
        return level - 1;
    }
}
