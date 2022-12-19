package custom.code_2022_12;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_1372
 * @Author Duys
 * @Description
 * @Date 2022/12/15 15:01
 **/
// 1372. 二叉树中的最长交错路径
public class LeetCode_1372 {
    int max;

    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        max = 0;
        process(root, true, 0);
        process(root, false, 0);
        return max;
    }

    // pre ture左边

    public void process(TreeNode root, boolean pre, int len) {
        max = Math.max(max, len);
        if (pre) { // 之前左边
            if (root.right != null) {
                process(root.right, false, len + 1);
            }
            if (root.left != null) {
                process(root.left, true, 1);
            }
        } else {
            // 之前右边
            if (root.right != null) {
                process(root.right, false, 1);
            }
            if (root.left != null) {
                process(root.left, true, len + 1);
            }
        }

    }
}
