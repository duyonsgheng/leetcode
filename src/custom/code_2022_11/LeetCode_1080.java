package custom.code_2022_11;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_1080
 * @Author Duys
 * @Description
 * @Date 2022/11/8 11:11
 **/
// 1080. 根到叶路径上的不足节点
public class LeetCode_1080 {

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (root == null) {
            return root;
        }
        int l = process(root.left, root.val, limit);
        int r = process(root.right, root.val, limit);
        if (l == Integer.MIN_VALUE || l + root.val < limit) {
            root.left = null;
        }
        if (r == Integer.MIN_VALUE || r + root.val < limit) {
            root.right = null;
        }
        int sum = Math.max(l, r) + root.val;
        return sum >= limit ? root : null;
    }

    public int process(TreeNode head, int sum, int limit) {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        int cur = head.val + sum;
        int l = process(head.left, cur, limit);
        int r = process(head.right, cur, limit);
        if (l == Integer.MIN_VALUE || cur + l < limit) {
            head.left = null;
        }
        if (r == Integer.MIN_VALUE || cur + r < limit) {
            head.right = null;
        }
        if (l == Integer.MIN_VALUE && r == Integer.MIN_VALUE) {
            return head.val;
        }
        return Math.max(l, r) + head.val;
    }
}
