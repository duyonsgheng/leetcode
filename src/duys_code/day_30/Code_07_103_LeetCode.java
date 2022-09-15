package duys_code.day_30;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName Code_07_103_LeetCode
 * @Author Duys
 * @Description T力扣：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 * @Date 2021/11/29 13:17
 **/
public class Code_07_103_LeetCode {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    // 使用队列，每一次一层进队列，一层出队列，然后收集
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int size = 0;
        boolean isHead = true;
        while (!queue.isEmpty()) {
            size = queue.size();
            List<Integer> step = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                // zigzag打印
                TreeNode cur = isHead ? queue.pollFirst() : queue.pollLast();
                step.add(cur.val);
                if (isHead) {
                    // 如果当前是顺序的，就添加到尾部
                    if (cur.left != null) {
                        queue.addLast(cur.left);
                    }
                    if (cur.right != null) {
                        queue.addLast(cur.right);
                    }
                } else {
                    // 需要反着打的时候，先添加右边的
                    if (cur.right != null) {
                        queue.addFirst(cur.right);
                    }
                    if (cur.left != null) {
                        queue.addFirst(cur.left);
                    }
                }
            }
            ans.add(step);
            isHead = !isHead;
        }
        return ans;
    }

}
