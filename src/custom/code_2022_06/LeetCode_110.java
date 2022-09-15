package custom.code_2022_06;

/**
 * @ClassName LeetCode_110
 * @Author Duys
 * @Description
 * @Date 2022/6/14 9:40
 **/
public class LeetCode_110 {
    public boolean isBalanced(TreeNode root) {
        return process(root) >= 0;
    }

    public static int process(TreeNode cur) {
        if (cur == null) {
            return 0;
        }
        int left = process(cur.left);
        int right = process(cur.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
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
