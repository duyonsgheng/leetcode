package custom.code_2023_06;

/**
 * @ClassName LeetCode_1895
 * @Author Duys
 * @Description
 * @Date 2023/6/25 10:23
 **/
// 1895. 最大的幻方
public class LeetCode_1895 {
    // 就枚举+前缀和
    int[][] rowsum, colsum; // 行和列的前缀和

    public int largestMagicSquare(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        rowsum = new int[n][m];
        colsum = new int[n][m];
        for (int i = 0; i < n; i++) {
            rowsum[i][0] = grid[i][0];
            for (int j = 1; j < m; j++) {
                rowsum[i][j] = rowsum[i][j - 1] + grid[i][j];
            }
        }
        for (int j = 0; j < m; j++) {
            colsum[0][j] = grid[0][j];
            for (int i = 1; i < n; i++) {
                colsum[i][j] = colsum[i - 1][j] + grid[i][j];
            }
        }
        int ans = Math.min(n, m);
        // 没觉每一个正方形
        for (int k = ans; k >= 2; k--) {
            for (int i = 0; i <= n - k; i++) {
                for (int j = 0; j <= m - k; j++) {
                    if (isOk(i, j, k, grid)) {
                        return k;
                    }
                }
            }
        }
        return 1;
    }

    // i 和 j为左上角点，长度为k的正方形
    public boolean isOk(int i, int j, int k, int[][] arr) {
        int endR = k + i - 1;
        int endC = k + j - 1;
        // 当前的和
        int len = rowsum[i][endC] - rowsum[i][j] + arr[i][j];
        // 行
        // a b c
        // 1 2 3
        for (int row = i + 1; row <= endR; row++) {
            if (len != rowsum[row][endC] - rowsum[row][j] + arr[row][j]) {
                return false;
            }
        }
        // 列
        // a b c
        // 1 2 3
        for (int col = j; col <= endC; col++) {
            if (len != colsum[endR][col] - colsum[i][col] + arr[i][col]) {
                return false;
            }
        }
        // 对角线1
        int cur = 0;
        for (int row = i, col = j; row <= endR && col <= endC; row++, col++) {
            col += arr[row][col];
        }
        if (cur != len) {
            return false;
        }
        // 对角线2
        cur = 0;
        for (int row = i, col = endC; row <= endR && col >= j; row++, col--) {
            cur += arr[row][col];
        }
        if (cur != len) {
            return false;
        }
        return true;
    }
}
