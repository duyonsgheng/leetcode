package custom.code_2023_07;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1937
 * @date 2023年07月10日
 */
// 1937. 扣分后的最大得分
// https://leetcode.cn/problems/maximum-number-of-points-with-cost/
public class LeetCode_1937 {
    public long maxPoints(int[][] points) {
        int n = points.length, m = points[0].length;
        long[][] dp = new long[n][m];
        for (int i = 0; i < m; i++) {
            dp[0][i] = points[0][i];
        }
        for (int i = 1; i < n; i++) {
            long max = 0;
            for (int j = 0; j < m; j++) {
                // 上一行的相同位置的
                max = Math.max(max - 1, dp[i - 1][j]);
                dp[i][j] = max + points[i][j];
            }
            max = 0;
            for (int j = m - 1; j >= 0; j--) {
                max = Math.max(max - 1, dp[i - 1][j]);
                dp[i][j] = Math.max(dp[i][j], max + points[i][j]);
            }
        }
        long ans = 0;
        for (int i = 0; i < m; i++) {
            ans = Math.max(ans, dp[n - 1][i]);
        }
        return ans;
    }
}
