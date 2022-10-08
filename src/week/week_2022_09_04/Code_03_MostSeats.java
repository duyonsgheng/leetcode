package week.week_2022_09_04;

/**
 * @ClassName Code_03_MostSeats
 * @Author Duys
 * @Description
 * @Date 2022/9/29 11:03
 **/
// 来自华为
// 给定一个二维数组map，代表一个餐厅，其中只有0、1两种值
// map[i][j] == 0 表示(i,j)位置是空座
// map[i][j] == 1 表示(i,j)位置坐了人
// 根据防疫要求，任何人的上、下、左、右，四个相邻的方向都不能再坐人
// 但是为了餐厅利用的最大化，也许还能在不违反防疫要求的情况下，继续安排人吃饭
// 请返回还能安排的最大人数
// 如果一开始的状况已经不合法，直接返回-1
// 比如:
// 1 0 0 0
// 0 0 0 1
// 不违反防疫要求的情况下，这个餐厅最多还能安排2人，如下所示，X是新安排的人
// 1 0 X 0
// 0 X 0 1
// 再比如:
// 1 0 0 0 0 1
// 0 0 0 0 0 0
// 0 1 0 0 0 1
// 0 0 0 0 0 0
// 不违反防疫要求的情况下，这个餐厅最多还能安排7人，如下所示，X是新安排的人
// 1 0 0 X 0 1
// 0 0 X 0 X 0
// 0 1 0 X 0 1
// X 0 X 0 X 0
// 数据范围 : 1 <= 矩阵的行、列 <= 20
public class Code_03_MostSeats {
    // 使用轮廓线dp
    // 一个状态status 代表了两个状态，当前来到i位置
    // 比如status -> 0 1 0 1 0 1 0 1
    // i 为2
    // 那么 0 到 1 就代表当前行的 i之前的状态，i位置及其以后的状态代表了上一行的状态，一个轮廓线的形式
    public static int mostSeats(int[][] map) {
        // 先把每一行 变成一个状态 ，代表了当前行的数据状况
        int n = map.length, m = map[0].length;
        int[] arr = new int[n];
        int status = 0;
        for (int i = 0; i < n; i++) {
            status = 0;
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    // 检查相邻位置
                    if (i > 0 && map[i - 1][j] == 1) {
                        return -1;
                    }
                    if (j > 0 && map[i][j - 1] == 1) {
                        return -1;
                    }
                }
                // 二进制的低位高位问题转换
                status |= map[i][j] << j;
            }
            arr[i] = status;
        }
        int[][][] dp = new int[n][m][1 << m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 1 << m; k++) {
                    dp[i][j][k] = -2;
                }
            }
        }
        int ans = process(arr, n, m, 0, 0, 0, dp);
        return ans == -1 ? 0 : ans;
    }

    // 轮廓线问题
    // 当前来到row col 前一行的状态是status
    public static int process(int[] arr, int n, int m, int row, int col, int status, int[][][] dp) {
        if (col == m) { // 最后一列，换行
            return process(arr, n, m, row + 1, 0, status, dp);
        }
        // 来到最后了
        if (row == n) {
            return 0; // 不需要安排人了
        }
        if (dp[row][col][status] != -2) {
            return dp[row][col][status];
        }
        // 左边状态
        int left = getStatus(status, col - 1, m);
        // 上边状态
        int up = getStatus(status, col, m);
        // 右边
        int right = getStatus(arr[row], col + 1, m);
        // 当前
        int cur = getStatus(arr[row], col, m);
        if (up == 1 && cur == 1) { // 已经相邻了
            return -1;
        }
        int p1 = -1;
        // 当前位置部安排新人
        if (cur == 1) {
            // 本来就是1，不需要安排
            p1 = process(arr, n, m, row, col + 1, status | (1 << col), dp);
        } else {
            // 原始位置没有人，本轮不安排，那么就需要把上边的状态同步到左边去
            p1 = process(arr, n, m, row, col + 1, status | (1 << col) ^ (1 << col), dp);
        }
        // 安排
        int p2 = -1;
        if (left + right + up + cur == 0) {
            // 安排了一个
            int next2 = process(arr, n, m, row, col + 1, status | (1 << col), dp);
            if (next2 != -1) {
                p2 = 1 + next2;
            }
        }
        int ans = Math.min(p1, p2);
        dp[row][col][status] = ans;
        return ans;
    }

    public static int getStatus(int status, int cur, int col) {
        return (cur < 0 || // 如果我左边没位置
                cur == col || // 如果我来到了最后一列，马上要换行了
                (status & (1 << cur)) == 0) // 本来就是0
                ? 0 : 1;
    }


}
