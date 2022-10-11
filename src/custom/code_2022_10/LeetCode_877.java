package custom.code_2022_10;

/**
 * @ClassName LeetCode_877
 * @Author Duys
 * @Description
 * @Date 2022/10/11 9:35
 **/
public class LeetCode_877 {

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        // 含义：当只有i到j范围上取石子
        // 对角线以上的无效，只有i<= j 的时候才有效
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 取i 或者 取 j
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] > 0;
    }


}
