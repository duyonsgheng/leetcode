package custom.code_2022_11;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1140
 * @Author Duys
 * @Description
 * @Date 2022/11/16 15:13
 **/
// 1140. 石子游戏 II
public class LeetCode_1140 {
    public int stoneGameII1(int[] piles) {
        int n = piles.length;
        // dp[i][j] 剩余i堆，m为j的情况下，最先拿的人能获得的最多石子
        int[][] dp = new int[n + 1][n + 1];
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += piles[i];
            for (int m = 1; m <= n; m++) {
                if (i + m * 2 >= n) { // 全部取走
                    dp[i][m] = sum;
                } else {
                    // 否则就枚举
                    for (int x = 1; x <= 2 * m; x++) {
                        // 我取了上一个人取剩下的，上一个只能从x位置开始取，
                        dp[i][m] = Math.max(dp[i][m], sum - dp[i + x][Math.max(m, x)]);
                    }
                }
            }
        }
        return dp[0][1];
    }

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + piles[i];
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(sum, 0, 1, dp);
    }

    public int process(int[] sum, int index, int m, int[][] dp) {
        if (dp[index][m] != -1) {
            return dp[index][m];
        }
        int rest = sum.length - 1 - index;
        // 如果不够了，则全部取走
        if (m * 2 >= rest) {
            // 当前的就是全部的减去先手拿到了index的那一部分
            return sum[sum.length - 1] - sum[index];
        }
        int ans = Integer.MIN_VALUE;
        for (int x = 1; x <= m * 2; x++) {
            if (x > rest) {
                continue;
            }
            int nextM = Math.max(x, m);
            // 当前取了index的，那么下一次就是x+Index开始
            int nextSum = process(sum, x + index, nextM, dp);
            ans = Math.max(ans, sum[sum.length - 1] - sum[index] - nextSum);
        }
        dp[index][m] = ans;
        return ans;
    }
}
