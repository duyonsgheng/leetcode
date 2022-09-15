package custom.code_2022_05;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_107
 * @Author Duys
 * @Description
 * @Date 2022/5/23 17:20
 **/
// 107. 二叉树的层序遍历 II
// 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
public class LeetCode_107 {
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        // 按层遍历，最后倒叙
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int tmp = queue.size() - 1;
            List<Integer> curLevel = new ArrayList<>();
            for (; tmp >= 0; tmp--) {
                TreeNode cur = queue.pollFirst();
                if (cur != null) {
                    curLevel.add(cur.val);
                }
                if (cur != null && cur.left != null) {
                    queue.addLast(cur.left);
                }
                if (cur != null && cur.right != null) {
                    queue.addLast(cur.right);
                }
            }
            ans.add(curLevel);
        }
        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode l1 = new TreeNode(9);
        TreeNode r1 = new TreeNode(20);
        root.left = l1;
        root.right = r1;
        l1.left = null;
        l1.right = null;
        r1.left = new TreeNode(15);
        r1.right = new TreeNode(7);
        List<List<Integer>> lists = levelOrderBottom(root);
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
