package custom.code_2022_05;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_106
 * @Author Duys
 * @Description
 * @Date 2022/5/23 16:56
 **/
// 106. 从中序与后序遍历序列构造二叉树
// 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
//链接：https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal
public class LeetCode_106 {

    static int index_post;

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length <= 0 || postorder == null || postorder.length <= 0) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();

        int n = inorder.length;
        index_post = postorder.length - 1;
        // 构造哈希映射，帮助我们快速定位根节点
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return process(inorder, postorder, 0, n - 1, indexMap);
    }

    // 9,3,15,20,7
    // 9,15,7,20,3
    // 3,9,20,null,null,15,7
    public static TreeNode process(int[] in, int[] post, int in_left, int in_right, Map<Integer, Integer> indexMap) {
        if (in_left > in_right) {
            return null;
        }
        // 后续遍历就是根节点
        int root_val = post[index_post];
        // 建立根节点
        TreeNode root = new TreeNode(root_val);
        // root所在的位置分成左右两棵树
        int index = indexMap.get(root_val);
        index_post--;
        root.right = process(in, post, index + 1, in_right, indexMap);
        root.left = process(in, post, in_left, index - 1, indexMap);
        return root;
    }

    public static void main(String[] args) {
        int[] in = {9, 3, 15, 20, 7};
        int[] post = {9, 15, 7, 20, 3};
        buildTree(in, post);
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
