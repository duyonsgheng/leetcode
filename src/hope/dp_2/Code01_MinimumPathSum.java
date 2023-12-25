package hope.dp_2;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code01_MinimumPathSum
 * @date 2023年12月11日 9:50
 */
// 最小路径和
// 给定一个包含非负整数的 m x n 网格 grid
// 请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
// 说明：每次只能向下或者向右移动一步。
// 测试链接 : https://leetcode.cn/problems/minimum-path-sum/
public class Code01_MinimumPathSum {
    // 可以用最短路径算法来做

    // 暴力递归
    public static int minPathSum1(int[][] grid) {
        return f1(grid, grid.length - 1, grid[0].length - 1);
    }

    // 从 0,0 到 i,j位置的最短路径和
    // 每次只能向下或者向右
    public static int f1(int[][] arr, int i, int j) {
        if (i == 0 && j == 0) {
            return arr[0][0];
        }
        int up = Integer.MAX_VALUE; // 从上边来到i,j
        int left = Integer.MAX_VALUE;// 从左边来到i,j
        if (i - 1 >= 0) {
            up = f1(arr, i - 1, j);
        }
        if (j - 1 >= 0) {
            left = f1(arr, i, j - 1);
        }
        return arr[i][j] + Math.min(left, up);
    }

    // 改二维dp
    public static int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        // 每一个格子依赖上边的格子和左边的格子
        dp[0][0] = grid[0][0];
        // 第一行，只依赖左边
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        // 第一列，只依赖右边
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 普遍位置，从左到右，从上到下填好
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }

    // 空间压缩
    // 一行一行的填
    public static int minPathSum2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dp = new int[m];
        // 每一个格子依赖上边的格子和左边的格子
        dp[0] = grid[0][0];
        // 第一行，只依赖左边
        for (int i = 1; i < m; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < m; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[m - 1];
    }
}
