package custom.code_2022_05;

/**
 * @ClassName LeetCode_64
 * @Author Duys
 * @Description
 * @Date 2022/5/9 17:58
 **/
// 64. 最小路径和
// 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//说明：每次只能向下或者向右移动一步。
public class LeetCode_64 {

    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length <= 0 || grid[0] == null || grid[0].length <= 0) {
            return -1;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(minPathSum(arr));
    }
}
