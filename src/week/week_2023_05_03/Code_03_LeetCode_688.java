package week.week_2023_05_03;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @ClassName Code_03_LeetCode_688
 * @Author Duys
 * @Description
 * @Date 2023/5/18 9:55
 **/
// 688. 骑士在棋盘上的概率
public class Code_03_LeetCode_688 {
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1d);
            }
        }
        return process(n, k, row, column, dp);
    }

    public double process(int n, int rest, int r, int c, double[][][] dp) {
        if (r < 0 || r >= n || c < 0 || c >= n) {
            return 0;
        }
        if (dp[r][c][rest] != -1) {
            return dp[r][c][rest];
        }
        double ans = 0;
        if (rest == 0) {
            ans = 1;
        } else {
            ans += (process(n, rest - 1, r - 2, c + 1, dp) / 8);
            ans += (process(n, rest - 1, r - 1, c + 2, dp) / 8);
            ans += (process(n, rest - 1, r + 1, c + 2, dp) / 8);
            ans += (process(n, rest - 1, r + 2, c + 1, dp) / 8);
            ans += (process(n, rest - 1, r + 2, c - 1, dp) / 8);
            ans += (process(n, rest - 1, r + 1, c - 2, dp) / 8);
            ans += (process(n, rest - 1, r - 1, c - 2, dp) / 8);
            ans += (process(n, rest - 1, r - 2, c - 1, dp) / 8);
        }
        dp[r][c][rest] = ans;
        return ans;
    }
}
