package duys_code.day_37;

/**
 * @ClassName Code_01_114_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 * @Date 2021/12/15 13:20
 **/
public class Code_01_114_LeetCode {
    // 二叉树展开未链表
    // 1 二叉树的递归套路
    public void flatten(TreeNode root) {
        process(root);
    }

    public static Info process(TreeNode head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        //   2
        // 3  4
        // 2 3 4
        head.left = null;
        head.right = leftInfo == null ? null : leftInfo.head;
        TreeNode tail = leftInfo == null ? head : leftInfo.tail;
        tail.right = rightInfo == null ? null : rightInfo.head;
        tail = rightInfo == null ? tail : rightInfo.tail;
        return new Info(head, tail);
    }

    public static class Info {
        public TreeNode head;
        public TreeNode tail;

        public Info(TreeNode h, TreeNode t) {
            head = h;
            tail = t;
        }
    }


    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            val = value;
        }
    }
}
