package custom.code_2022_10;

import java.util.Arrays;

/**
 * @ClassName LeetCode_983
 * @Author Duys
 * @Description
 * @Date 2022/10/26 14:55
 **/
// 983. 最低票价
public class LeetCode_983 {
    // 动态规划
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int maxD = days[n - 1];
        int minD = days[0];
        int[] dp = new int[maxD + 31];
        for (int d = maxD, i = n - 1; d >= minD; d--) {
            if (d == days[i]) { // 要出去旅游
                //dp[d] = Math.min(dp[d + 1] + costs[0], Math.min(dp[d + 7] + costs[1], dp[d + 30] + costs[2]));
                dp[d] = Math.min(dp[d + 1] + costs[0], dp[d + 7] + costs[1]);
                dp[d] = Math.min(dp[d], dp[d + 30] + costs[2]);
                i--;
            } else { // 不需要出去
                dp[d] = dp[d + 1];
            }
        }
        return dp[minD];
    }
}
