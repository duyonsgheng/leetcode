package duys_code.day_30;

/**
 * @ClassName Code_08_108_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 * @Date 2021/11/29 13:42
 **/
public class Code_08_108_LeetCode {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 将有序数组转为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        return process(nums, 0, nums.length - 1);
    }

    // 在数组上二分
    public static TreeNode process(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        if (l == r) {
            return new TreeNode(nums[l]);
        }
        int m = l + ((r - l) >> 1);
        TreeNode root = new TreeNode(nums[m]);
        root.left = process(nums, l, m - 1);
        root.right = process(nums, m + 1, r);
        return root;
    }

}
