package week.week_2022_01_01;

import java.util.Arrays;

/**
 * @ClassName Code_03_LeetCode_1770
 * @Author Duys
 * @Description
 * @Date 2022/4/12 17:06
 **/
// 给你两个长度分别 n 和 m 的整数数组 nums 和 multipliers ，其中 n >= m ，数组下标 从 1 开始 计数。
//初始时，你的分数为 0 。你需要执行恰好 m 步操作。在第 i 步操作（从 1 开始 计数）中，需要：
//选择数组 nums 开头处或者末尾处 的整数 x 。
//你获得 multipliers[i] * x 分，并累加到你的分数中。
//将 x 从数组 nums 中移除。
//在执行 m 步操作后，返回 最大 分数。
//链接：https://leetcode-cn.com/problems/maximum-score-from-performing-multiplication-operations
public class Code_03_LeetCode_1770 {
    // 二话不说先来尝试
    public int maximumScore1(int[] nums, int[] multipliers) {
        if (nums == null || nums.length <= 0 || multipliers == null || multipliers.length <= 0 || multipliers.length > nums.length) {
            return -1;
        }
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return max1(nums, multipliers, 0, nums.length - 1, dp);
    }

    // 1 2 3 4 5
    // l = 0 r = 4 size = 5
    public static int max1(int[] a, int[] b, int l, int r, int[][] dp) {

        // 换算出当前走了多少步
        int indexB = l + a.length - r - 1;
        if (indexB == b.length) {
            return 0;
        }
        int ans = 0;
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        // 拿开头的
        int p1 = a[l] * b[indexB] + max1(a, b, l + 1, r, dp);
        // 拿结尾的
        int p2 = a[r] * b[indexB] + max1(a, b, l, r - 1, dp);
        ans = Math.max(p1, p2);
        dp[l][r] = ans;
        return ans;
    }

    public int maximumScore(int[] nums, int[] multipliers) {
        if (nums == null || nums.length <= 0 || multipliers == null || multipliers.length <= 0 || multipliers.length > nums.length) {
            return -1;
        }
        int n = nums.length;
        int m = multipliers.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int l = m - 1; l >= 0; l--) {
            for (int j = l + 1; j <= m; j++) {
                int r = n - m + j - 1;
                int indexb = l + n - r - 1;
                dp[l][j] = Math.max(nums[l] * multipliers[indexb] + dp[l + 1][j], nums[r] * multipliers[indexb] + dp[l][j - 1]);
            }
        }
        return dp[0][m];
    }
}


