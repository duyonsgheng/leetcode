package custom.code_2022_08;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_654
 * @Author Duys
 * @Description
 * @Date 2022/8/30 9:25
 **/
// 654. 最大二叉树
public class LeetCode_654 {

    // 找到最大值
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    public static TreeNode process(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        if (r > nums.length - 1) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        int j = -1;
        for (int i = l; i <= r; i++) {
            if (nums[i] >= max) {
                max = nums[i];
                j = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = process(nums, l, j - 1);
        root.right = process(nums, j + 1, r);
        return root;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 6, 0, 5};
        TreeNode treeNode = constructMaximumBinaryTree(arr);
        System.out.println();
    }
}
