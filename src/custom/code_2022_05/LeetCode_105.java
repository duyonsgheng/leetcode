package custom.code_2022_05;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @ClassName LeetCode_105
 * @Author Duys
 * @Description
 * @Date 2022/5/18 17:30
 **/
// 105. 从前序与中序遍历序列构造二叉树
// 给定两个整数数组preorder 和 inorder，其中preorder 是二叉树的先序遍历， inorder是同一棵树的中序遍历，请构造二叉树并返回其根节点。
//链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
public class LeetCode_105 {
    // 通过先序遍历：先遍历根节点，然后左孩子，然后右孩子
    // 通过中序遍历：先遍历左孩子，然后根节点，然后右孩子

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length <= 0 || inorder == null || inorder.length <= 0) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();

        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        for (int i = 0; i < n; ++i) {
            indexMap.put(inorder[i], i);
        }
        return process(preorder, inorder, 0, n - 1, 0, n - 1, indexMap);
    }

    public static TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length <= 0 || inorder == null || inorder.length <= 0) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(null);
        for (int i : preorder) {
            queue.add(i + "");
        }
        queue.add(null);
        return prcess1(queue);
    }

    public static TreeNode prcess1(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String value = queue.poll();
        if (value == null) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.valueOf(value));
        head.left = prcess1(queue);
        head.right = prcess1(queue);
        return head;
    }

    public static TreeNode process(int[] pre, int[] in, int pre_left, int pre_right, int in_left, int in_right, Map<Integer, Integer> indexMap) {
        if (pre_left > pre_right) {
            return null;
        }
        // 先序遍历第一个节点就是根节点
        int pre_root = pre_left;
        int in_root = indexMap.get(pre[pre_root]);
        // 建立根节点
        TreeNode root = new TreeNode(pre[pre_root]);
        // 左子树有多少，去中序遍历里面拿我左边一共多少节点数
        int leftSize = in_root - in_left;
        // 然后递归找构建左子树，然后连接到当前根上
        // preorder = [3,9,20,15,7],
        // inorder = [9,3,15,20,7]
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = process(pre, in, pre_left + 1, pre_left + leftSize, in_left, in_root - 1, indexMap);
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = process(pre, in, pre_left + leftSize + 1, pre_right, in_root + 1, in_right, indexMap);
        return root;
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
