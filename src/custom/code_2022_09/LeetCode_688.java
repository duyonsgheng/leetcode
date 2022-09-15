package custom.code_2022_09;

/**
 * @ClassName LeetCode_688
 * @Author Duys
 * @Description
 * @Date 2022/9/7 15:43
 **/
// 688. 骑士在棋盘上的概率
public class LeetCode_688 {
    // 八个方向
    int[][] dir = {{1, 2}, {1, -2}, {-1, -2}, {-1, 2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[k + 1][n][n];
        for (int step = 0; step <= k; step++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (step == 0) {
                        // i,j 都在棋盘上，一步没走，那肯定在棋盘上，所以概率是100%
                        dp[step][i][j] = 1;
                    } else {
                        for (int m = 0; m < dir.length; m++) {
                            int x = i + dir[m][0];
                            int y = j + dir[m][1];
                            if (x >= 0 && x < n && y >= 0 && y < n) {
                                dp[step][i][j] += dp[step - 1][x][y] / 8;
                            }
                        }
                    }
                }
            }
        }
        return dp[k][row][column];
    }
}
