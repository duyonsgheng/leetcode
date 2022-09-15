package custom.code_2022_06;

/**
 * @ClassName LeetCode_112
 * @Author Duys
 * @Description
 * @Date 2022/6/14 11:20
 **/
public class LeetCode_112 {

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        return process(root, targetSum);
    }

    public static boolean process(TreeNode root, int rest) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == rest;
        }
        return process(root.left, rest - root.val) | process(root.right, rest - root.val);
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
