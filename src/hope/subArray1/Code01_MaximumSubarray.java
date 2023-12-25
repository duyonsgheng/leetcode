package hope.subArray1;

/**
 * @author Mr.Du
 * @ClassName Code01_MaximumSubarray
 * @date 2023年12月25日 16:13
 */
// 子数组最大累加和
// 给你一个整数数组 nums
// 返回非空子数组的最大累加和
// 测试链接 : https://leetcode.cn/problems/maximum-subarray/
public class Code01_MaximumSubarray {
    // 普通动态规划
    public static int maxSubArray1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // 空间压缩
    public static int maxSubArray2(int[] nums) {
        int n = nums.length;
        int ans = nums[0];
        for (int i = 1, pre = nums[0]; i < n; i++) {
            pre = Math.max(nums[i], pre + nums[i]);
            ans = Math.max(ans, pre);
        }
        return ans;
    }

    // 附加问题实现
    public static int left;
    public static int right;
    public static int sum;

    public static void extra(int[] nums) {
        sum = Integer.MIN_VALUE;
        for (int l = 0, r = 0, pre = Integer.MIN_VALUE; r < nums.length; r++) {
            if (pre >= 0) {
                pre += nums[r];
            } else {
                pre = nums[r];
                l = r;
            }
            if (pre > sum) {
                sum = pre;
                left = l;
                right = r;
            }
        }
    }
}
