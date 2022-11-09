package custom.code_2022_11;

/**
 * @ClassName LeetCode_1049
 * @Author Duys
 * @Description
 * @Date 2022/11/8 9:30
 **/
// 1049. 最后一块石头的重量 II
public class LeetCode_1049 {

    // 背包问题了，怎么凑出 小于= sum/2 的最大
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int num : stones) {
            sum += num;
        }
        int m = sum / 2;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            int cur = stones[i - 1];
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= cur) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cur] + cur);
                }
            }
        }
        return Math.abs(sum - (1 << dp[n][m]));
    }
}
