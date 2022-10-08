package week.week_2022_09_04;

/**
 * @ClassName Code_02_SetAllOneMinTimes
 * @Author Duys
 * @Description
 * @Date 2022/9/29 10:08
 **/
// 来自华为
// 一个n*n的二维数组中，只有0和1两种值
// 当你决定在某个位置操作一次
// 那么该位置的行和列整体都会变成1，不管之前是什么状态
// 返回让所有值全变成1，最少的操作次数
// 1 < n < 10，没错！原题就是说n < 10, 不会到10！最多到9！
public class Code_02_SetAllOneMinTimes {

    // 思路，我们把每一行的数变成一个状态。然后用一个数组来记录

    public static int setOneMinTimes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int status = 0;
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) status |= 1 << j;
            }
            arr[i] = status;
        }
        // 四个可变参数
        int[][][][] dp = new int[1 << n][1 << m][n][m];
        for (int a = 0; a < (1 << n); a++) {
            for (int b = 0; b < (1 << m); b++) {
                for (int c = 0; c < n; c++) {
                    for (int d = 0; d < m; d++) {
                        dp[a][b][c][d] = -1;
                    }
                }
            }
        }
        return process(arr, n, m, 0, 0, 0, 0, dp);
    }

    public static int process(int[] arr, int n, int m, int rowStatus, int colStatus, int row, int col, int[][][][] dp) {
        if (row == n) { // 最后了
            for (int i = 0; i < n; i++) {
                // 当前行没点击
                // 并且列上也没
                // 那么完成不了了
                if ((rowStatus & (1 << i)) == 0 && (arr[i] | colStatus) != (1 << m) - 1) {
                    return Integer.MAX_VALUE;
                }
            }
            // 否则，就之前点击了已经满足了需求，不需要再次点击了
            return 0;
        }
        // 列到头了，需要换行了
        if (col == m) {
            return process(arr, n, m, rowStatus, colStatus, row + 1, 0, dp);
        }
        if (dp[rowStatus][colStatus][row][col] != -1) {
            return dp[rowStatus][colStatus][row][col];
        }
        // 当前不点
        int p1 = process(arr, n, m, rowStatus, colStatus, row, col + 1, dp);
        int p2 = Integer.MAX_VALUE;
        // 点击
        int next2 = process(arr, n, m, rowStatus | (1 << row), colStatus | (1 << col), row, col + 1, dp);
        if (next2 != Integer.MAX_VALUE) {
            p2 = 1 + next2;
        }
        int ans = Math.min(p1, p2);
        dp[rowStatus][colStatus][row][col] = ans;
        return ans;
    }
}
