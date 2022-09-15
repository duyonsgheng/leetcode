package duys_code.day_22;

/**
 * @ClassName Code_02_42_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/trapping-rain-water/
 * @Date 2021/11/10 16:10
 **/
public class Code_02_42_LeetCode {
    /**
     * 接雨水问题1：给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     */
    /**
     * 使用双指针：
     * 1.left  卡住arr[0]
     * 2.right 卡住arr[n-1]
     * 3.往中间走。往中间走的过程中两个指针谁遇到遇到大的值就交换并且结算
     */

    public int trap(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int n = arr.length;
        int leftMax = arr[0];
        int rightMax = arr[n - 1];
        int l = 1;
        int r = n - 1;
        int ans = 0;
        while (l <= r) {
            // 因为我左边是最大是leftMax，右边的最大是rightMax
            // 那么从左往右，最大值至少是leftMax，从右往左走，最大值至少是rightMax
            // 所以边走变结算，并且遇到大的就交换
            // 如果左边的最大值是小于右边的，结算左边的，并且左边的往右走
            if (leftMax <= rightMax) {
                ans += Math.max(0, leftMax - arr[l]);
                leftMax = Math.max(leftMax, arr[l++]);
            }
            // 如果右边的大，那么结算右边的，然后往中间走
            else {
                ans += Math.max(0, rightMax - arr[r]);
                rightMax = Math.max(rightMax, arr[r--]);
            }
        }
        return ans;
    }
}
