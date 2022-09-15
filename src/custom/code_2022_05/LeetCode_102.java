package custom.code_2022_05;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName LeetCode_102
 * @Author Duys
 * @Description
 * @Date 2022/5/18 13:33
 **/
//102. 二叉树的层序遍历
//  给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
public class LeetCode_102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 使用栈
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 每次搞一层
        stack.push(root);
        while (!stack.isEmpty()) {
            int cur = stack.size();
            Deque<TreeNode> stack1 = new ArrayDeque<>();
            List<Integer> curAns = new ArrayList<>();
            for (int i = 0; i < cur; i++) {
                TreeNode pop = stack.pollFirst();
                curAns.add(pop.val);
                if (pop.left != null) {
                    stack1.addLast(pop.left);
                }
                if (pop.right != null) {
                    stack1.addLast(pop.right);
                }
            }
            ans.add(curAns);
            stack = stack1;
        }
        return ans;
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
