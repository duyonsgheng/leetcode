package custom.code_2023_07;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1959
 * @date 2023年07月14日
 */
// https://leetcode.cn/problems/minimum-total-space-wasted-with-k-resizing-operations/
// 1959. K 次调整数组大小浪费的最小总空间
public class LeetCode_1959 {
    // 把数组分为 k+1个区间
    // 每个区间取得当前区间数组的最大值
    public int minSpaceWastedKResizing(int[] nums, int k) {
        int max = Integer.MAX_VALUE / 2;
        int n = nums.length;
        int sum = 0;
        int[][] dp = new int[n][k + 2];
        int[][] premax = new int[n][n];
        for (int i = 0; i < n; i++) {
            int m = 0;
            for (int j = i; j < n; j++) {
                // 当前区间的最大值
                // 当前区间的和
                m = Math.max(m, nums[j]);
                premax[i][j] = m * (j - i + 1);
            }
            sum += nums[i];
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], max);
        }
        for (int i = 0; i < n; i++) {
            // 划分区间 0到i数组上划分区间
            for (int j = 1; j <= k + 1; k++) {
                for (int l = 0; l <= i; l++) {
                    dp[i][j] = Math.min(dp[i][j], (l == 0 ? 0 : dp[l - 1][j - 1]) + premax[l][i]);
                }
            }
        }
        // 只算增量
        return dp[n - 1][k + 1] - sum;
    }
}

