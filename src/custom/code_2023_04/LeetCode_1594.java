package custom.code_2023_04;

/**
 * @ClassName LeetCode_1594
 * @Author Duys
 * @Description
 * @Date 2023/4/17 17:09
 **/
// 1594. 矩阵的最大非负积
public class LeetCode_1594 {
    int mod = 1_000_000_007;

    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long[][] max = new long[m][n];
        long[][] min = new long[m][n];
        max[0][0] = min[0][0] = grid[0][0];
        // 一直向下
        for (int i = 1; i < m; i++) {
            max[i][0] = min[i][0] = max[i - 1][0] * grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            max[0][i] = min[0][i] = max[0][i - 1] * grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] >= 0) {
                    // 从左边来的，从上边来的
                    max[i][j] = Math.max(max[i][j - 1], max[i - 1][j]) * grid[i][j];
                    min[i][j] = Math.min(min[i][j - 1], min[i - 1][j]) * grid[i][j];
                } else {
                    max[i][j] = Math.min(min[i][j - 1], min[i - 1][j]) * grid[i][j];
                    min[i][j] = Math.max(max[i][j - 1], max[i - 1][j]) * grid[i][j];
                }
            }
        }
        if (max[m - 1][n - 1] < 0) {
            return -1;
        } else {
            return (int) (max[m - 1][n - 1] % mod);
        }
    }
}
