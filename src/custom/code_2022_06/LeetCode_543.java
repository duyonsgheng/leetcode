package custom.code_2022_06;

/**
 * @ClassName leetCode_543
 * @Author Duys
 * @Description
 * @Date 2022/6/13 15:51
 **/
// 543. 二叉树的直径
// 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点
public class LeetCode_543 {



    static int ans = 1;

    public static int diameterOfBinaryTree1(TreeNode root) {
        process1(root);
        return ans - 1;
    }

    public static int process1(TreeNode cur) {
        if (cur == null) {
            return 0;
        }
        int l = process1(cur.left);
        int r = process1(cur.right);
        ans = Math.max(ans, l + r + 1);
        return Math.max(l, r) + 1;
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
