package custom.code_2022_07;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LeetCode_199
 * @Author Duys
 * @Description
 * @Date 2022/7/6 13:48
 **/
// 199. 二叉树的右视图
// 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
public class LeetCode_199 {

    // 收集每一层的最右节点
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 用当前层的带出下一层
            TreeNode last = queue.pollLast();
            ans.add(last.val);
            LinkedList<TreeNode> queue1 = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode treeNode = queue.pollFirst();
                if (treeNode.left != null) {
                    queue1.addLast(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue1.addLast(treeNode.right);
                }
            }
            if (last.left != null) {
                queue1.addLast(last.left);
            }
            if (last.right != null) {
                queue1.addLast(last.right);
            }
            queue = queue1;
        }
        return ans;
    }

    public static void main(String[] args) {
        // [1,2,3,null,5,null,4]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = null;
        root.left.right = new TreeNode(5);
        root.right.left = null;
        root.right.right = new TreeNode(4);
        rightSideView(root);
    }

}
