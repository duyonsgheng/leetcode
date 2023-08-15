package custom.code_2023_08;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2681
 * @date 2023年08月01日
 */
// 2681. 英雄的力量
// https://leetcode.cn/problems/power-of-heroes/description/
public class LeetCode_2681 {
    // 动态规划
    public int sumOfPower(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        int[] sum = new int[n + 1];
        int mod = 1_000_000_007;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = (nums[i] + sum[i]) % mod;
            sum[i + 1] = (sum[i] + dp[i]) % mod;
            ans = (int) ((ans + (long) nums[i] * nums[i] % mod * dp[i]) % mod);
            ans += ans < 0 ? mod : 0;
        }
        return ans;
    }
}
