package duys_code.day_14;

/**
 * @ClassName Code_04_99_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/recover-binary-search-tree/
 * @Date 2021/10/25 13:22
 **/
public class Code_04_99_LeetCode {
    /**
     * 二叉搜索树中有两个节点出现问题。请恢复这颗树
     */
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }

    /**
     * 一颗搜索二叉树的中序遍历是升序的
     */
    public static void recoverTree(TreeNode root) {
        TreeNode[] treeNodes = towErrors(root);
        if (treeNodes[0] != null && treeNodes[1] != null) {
            int tmp = treeNodes[0].val;
            treeNodes[0].val = treeNodes[1].val;
            treeNodes[1].val = tmp;
        }
    }

    public static TreeNode[] towErrors(TreeNode head) {
        TreeNode[] treeNodes = new TreeNode[2];
        TreeNode cur = head;
        TreeNode mostRight = null;
        TreeNode pre = null;
        TreeNode e1 = null;
        TreeNode e2 = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right != null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else mostRight.right = null;
            }
            if (pre != null && pre.val > cur.val) {
                e1 = e1 == null ? pre : e1;
                e2 = cur;
            }
            pre = cur;
            cur = cur.right;
        }
        treeNodes[0] = e1;
        treeNodes[1] = e2;
        return treeNodes;
    }
}
