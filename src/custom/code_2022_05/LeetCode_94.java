package custom.code_2022_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_94
 * @Author Duys
 * @Description
 * @Date 2022/5/17 16:45
 **/
//94. 二叉树的中序遍历
public class LeetCode_94 {

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        in(root, ans);
        return ans;
    }

    public static void in(TreeNode head, List<Integer> ans) {
        if (head == null) {
            return;
        }
        in(head.left, ans);
        ans.add(head.val);
        in(head.right, ans);
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
