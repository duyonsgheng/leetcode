package hope.dp_2;

/**
 * @author Mr.Du
 * @ClassName Code06_LongestIncreasingPath
 * @date 2023年12月18日 16:12
 */
// 矩阵中的最长递增路径
// 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度
// 对于每个单元格，你可以往上，下，左，右四个方向移动
// 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）
// 测试链接 : https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
public class Code06_LongestIncreasingPath {
    public static int longestIncreasingPath1(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

            }
        }
        return ans;
    }

    public static int f1(int[][] arr, int i, int j) {
        int next = 0;
        if (i > 0 && arr[i][j] < arr[i - 1][j]) {
            next = Math.max(next, f1(arr, i - 1, j));
        }
        if (i + 1 < arr.length && arr[i][j] < arr[i + 1][j]) {
            next = Math.max(next, f1(arr, i + 1, j));
        }
        if (j > 0 && arr[i][j] < arr[i][j - 1]) {
            next = Math.max(next, f1(arr, i, j - 1));
        }
        if (j + 1 < arr[0].length && arr[i][j] < arr[i][j + 1]) {
            next = Math.max(next, f1(arr, i, j + 1));
        }
        return next + 1;
    }

    public static int longestIncreasingPath2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, f2(grid, i, j, dp));
            }
        }
        return ans;
    }

    public static int f2(int[][] grid, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int next = 0;
        if (i > 0 && grid[i][j] < grid[i - 1][j]) {
            next = Math.max(next, f2(grid, i - 1, j, dp));
        }
        if (i + 1 < grid.length && grid[i][j] < grid[i + 1][j]) {
            next = Math.max(next, f2(grid, i + 1, j, dp));
        }
        if (j > 0 && grid[i][j] < grid[i][j - 1]) {
            next = Math.max(next, f2(grid, i, j - 1, dp));
        }
        if (j + 1 < grid[0].length && grid[i][j] < grid[i][j + 1]) {
            next = Math.max(next, f2(grid, i, j + 1, dp));
        }
        dp[i][j] = next + 1;
        return next + 1;
    }
}
