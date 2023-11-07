package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1444
 * @date 2023年08月18日
 */
// 1444. 切披萨的方案数
public class LeetCode_1444 {
    // 容斥原理
    public int ways(String[] pizza, int k) {
        int n = pizza.length, m = pizza[0].length(), mod = 1_000_000_007;
        int[][] apples = new int[n + 1][m + 1];
        int[][][] dp = new int[k + 1][n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                apples[i][j] = apples[i][j + 1] + apples[i + 1][j] - apples[i + 1][j + 1] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
                dp[1][i][j] = apples[i][j] > 0 ? 1 : 0;
            }
        }
        for (int k1 = 2; k1 <= k; k1++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // 水平
                    for (int i1 = i + 1; i1 < n; i1++) {
                        if (apples[i][j] > apples[i1][j]) {
                            dp[k1][i][j] = (dp[k1][i][j] + dp[k1 - 1][i1][j]) % mod;
                        }
                    }
                    // 垂直
                    for (int j1 = j + 1; j1 < m; j1++) {
                        if (apples[i][j] > apples[i][j1]) {
                            dp[k1][i][j] = (dp[k1][i][j] + dp[k1 - 1][i][j1]) % mod;
                        }
                    }
                }
            }
        }
        return dp[k][0][0];
    }
}
