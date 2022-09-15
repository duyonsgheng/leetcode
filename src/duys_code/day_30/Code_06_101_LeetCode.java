package duys_code.day_30;

/**
 * @ClassName Code_06_101_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/symmetric-tree/
 * @Date 2021/11/29 13:06
 **/
public class Code_06_101_LeetCode {
    // 对称树

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    public static boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null) {
            return left.val == right.val && isMirror(left.left, right.right) && isMirror(left.right, right.left);
        }
        return false;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
