package custom.code_2022_05;

/**
 * @ClassName LeetCode_104
 * @Author Duys
 * @Description
 * @Date 2022/5/18 17:17
 **/
//104. 二叉树的最大深度
// 给定一个二叉树，找出其最大深度。
//二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
//说明: 叶子节点是指没有子节点的节点。
public class LeetCode_104 {
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root);
    }

    public static int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = process(root.left);
        int right = process(root.right);
        return Integer.max(left, right) + 1;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
