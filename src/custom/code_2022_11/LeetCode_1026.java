package custom.code_2022_11;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_1026
 * @Author Duys
 * @Description
 * @Date 2022/11/4 9:37
 **/
// 1026. 节点与其祖先之间的最大差值
public class LeetCode_1026 {

    public static int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root, root.val, root.val);
    }

    public static int process(TreeNode head, int min, int max) {
        if (head == null) {
            return 0;
        }
        int curMax = Math.max(Math.abs(head.val - min), Math.abs(head.val - max));
        int left = 0, right = 0;
        if (head.val < min) {
            left = process(head.left, head.val, max);
            right = process(head.right, head.val, max);
        } else if (head.val > max) {
            left = process(head.left, min, head.val);
            right = process(head.right, min, head.val);
        } else {
            left = process(head.left, min, max);
            right = process(head.right, min, max);
        }
        return Math.max(curMax, Math.max(left, right));
    }

    public static void main(String[] args) {
        // root = [8,3,10,1,6,null,14,null,null,4,7,13]
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);

        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);

        System.out.println(maxAncestorDiff(root));
    }
}
