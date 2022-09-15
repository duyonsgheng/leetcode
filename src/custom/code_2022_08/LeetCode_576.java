package custom.code_2022_08;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LeetCode_576
 * @Author Duys
 * @Description
 * @Date 2022/8/26 17:20
 **/
//576. 出界的路径数
public class LeetCode_576 {

    static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int mod = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        return process(startRow, startColumn, maxMove, m, n);
    }

    // 从当前位置出发，有多少方法
    public static int process(int curRow, int curCol, int rest, int m, int n) {
        if (curRow <= 0 || curCol <= 0 || curRow >= m || curCol >= n) {
            return 1;
        }
        if (rest == 0) {
            return 0;
        }
        // 剪枝：如果小球不管怎么移动都无法越出网格，那就剪掉这个枝
        if (curRow - rest >= 0 && curCol - rest >= 0 && curRow + rest < m && curCol + rest < n) {
            return 0;
        }

        int sum = 0;
        for (int[] dir : dirs) {
            sum = (sum + process(curRow + dir[0], curCol + dir[1], rest - 1, m, n)) % mod;
        }
        return sum;
    }

    public int findPaths1(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[m][n][maxMove + 1];
        // 移动步数2的都是从移动步数1的转移来的
        // 移动步数3的都是从移动步数2的转移来的
        // 所以，要从移动步数从1开始递增
        for (int k = 1; k <= maxMove; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 处理四条边
                    if (i == 0) dp[i][j][k]++;
                    if (j == 0) dp[i][j][k]++;
                    if (i == m - 1) dp[i][j][k]++;
                    if (j == n - 1) dp[i][j][k]++;

                    // 中间的位置，向四个方向延伸
                    for (int[] dir : dirs) {
                        int nextI = i + dir[0];
                        int nextJ = j + dir[1];
                        if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n) {
                            dp[i][j][k] = (dp[i][j][k] + dp[nextI][nextJ][k - 1]) % mod;
                        }
                    }
                }
            }
        }

        return dp[startRow][startColumn][maxMove];
    }
}
