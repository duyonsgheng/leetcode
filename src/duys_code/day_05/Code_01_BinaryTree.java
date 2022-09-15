package duys_code.day_05;

import java.util.Stack;

/**
 * @ClassName Code_01_BinaryTree
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * @Date 2021/9/23 10:21
 **/
public class Code_01_BinaryTree {
    /**
     * 题意：
     * 已知一棵搜索二叉树上没有重复值的节点，
     * 搜索二叉树，左树每一个节点比我小，右树上每一个节点比我大
     * 现在有一个数组arr，是这棵搜索二叉树先序遍历的结果
     * 请根据arr生成整棵树并返回头节点
     */

    public static TreeNode bstFromPreorder1(int[] preorder) {
        if (preorder == null || preorder.length < 1) {
            return null;
        }
        return process1(preorder, 0, preorder.length - 1);
    }

    public static TreeNode process1(int[] arr, int L, int R) {
        if (L > R) {
            return null;
        }
        // 思路是，从当前L位置为头，遍历到第一个大于L位置的树 L+1 ~ index-1 左树，index ~ R 右树
        TreeNode head = new TreeNode(arr[L]);
        int bigIndex = L + 1;
        for (; bigIndex <= R; bigIndex++) {
            if (arr[bigIndex] > arr[L]) {
                break;
            }
        }
        head.left = process1(arr, L + 1, bigIndex);
        head.right = process1(arr, bigIndex + 1, R);
        return head;
    }


    // 最优解--单调栈
    public static TreeNode bstFromPreorder2(int[] preorder) {
        if (preorder == null || preorder.length < 1) {
            return null;
        }
        // 可以使用数组来替代Stack
        Stack<Integer> stack = new Stack<>();
        int[] bigArr = new int[preorder.length];
        for (int i = 0; i < bigArr.length; i++) {
            bigArr[i] = -1;
        }
        for (int i = 0; i < preorder.length; i++) {
            while (!stack.isEmpty() && preorder[stack.peek()] < preorder[i]) {
                bigArr[stack.pop()] = i;
            }
            stack.push(i);
        }
        return process2(preorder, 0, preorder.length - 1, bigArr);
    }

    public static TreeNode process2(int[] arr, int L, int R, int[] bigArr) {
        if (L > R) {
            return null;
        }
        // 思路是，从当前L位置为头，遍历到第一个大于L位置的树 L+1 ~ index-1 左树，index ~ R 右树
        TreeNode head = new TreeNode(arr[L]);
        int bigIndex = (bigArr[L] == -1 || bigArr[L] > R) ? R + 1 : bigArr[L];
        head.left = process2(arr, L + 1, bigIndex - 1, bigArr);
        head.right = process2(arr, bigIndex, R, bigArr);
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {8, 5, 1, 7, 10, 12};
        bstFromPreorder2(arr);
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
