package week.week_2022_10_04;

/**
 * @ClassName Code_02_MostTrailingZerosTurnLeftOfRightAtMostOnce
 * @Author Duys
 * @Description
 * @Date 2022/10/27 11:02
 **/
// 给定一个二维数组matrix
// 每个格子都是正数，每个格子都和上、下、左、右相邻
// 你可以从任何一个格子出发，走向相邻的格子
// 把沿途的数字乘起来，希望得到的最终数字中，结尾的0最多
// 走的过程中，向左走或者向右走的拐点，最多只能有一次
// 返回结尾最多的0，能是多少
// 1 <= 行、列 <= 400
public class Code_02_MostTrailingZerosTurnLeftOfRightAtMostOnce {
    // 枚举每一个点作为拐点
    // 那么每一个点有上下左右四个方向上，找出2的数量 和 5的数量
    // 然后从这个4个中选出2个，就6种组合。

    public static int mostTrailingZeros(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] f2 = new int[n][m]; // 自己有几个2得因子
        int[][] f5 = new int[n][m]; // 自己有几个5得因子
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                f2[i][j] = factors(matrix[i][j], 2);
                f5[i][j] = factors(matrix[i][j], 5);
            }
        }
        int[][] left2 = new int[n][m]; // 左边多少2
        int[][] left5 = new int[n][m]; // 左边多少5
        int[][] up2 = new int[n][m];   // 上边多少2
        int[][] up5 = new int[n][m];   // 上边多少5
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                left2[i][j] = f2[i][j] + (j > 0 ? left2[i][j - 1] : 0);
                left5[i][j] = f5[i][j] + (j > 0 ? left5[i][j - 1] : 0);
                up2[i][j] = f2[i][j] + (i > 0 ? up2[i - 1][j] : 0);
                up5[i][j] = f5[i][j] + (i > 0 ? up5[i - 1][j] : 0);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int l2 = j == 0 ? 0 : left2[i][j - 1];
                int l5 = j == 0 ? 0 : left5[i][j - 1];
                int r2 = left2[i][m - 1] - left2[i][j];
                int r5 = left5[i][m - 1] - left5[i][j];
                int u2 = i == 0 ? 0 : up2[i - 1][j];
                int u5 = i == 0 ? 0 : up5[i - 1][j];
                int d2 = up2[n - 1][j] - up2[i][j];
                int d5 = up5[n - 1][j] - up5[i][j];

                // 左上
                int p1 = Math.min(l2 + u2 + f2[i][j], l5 + u5 + f5[i][j]);
                // 左下
                int p2 = Math.min(l2 + d2 + f2[i][j], l5 + d5 + f5[i][j]);
                // 右上
                int p3 = Math.min(r2 + u2 + f2[i][j], r5 + u5 + f5[i][i]);
                // 右下
                int p4 = Math.min(r2 + d2 + f2[i][j], r5 + d5 + f5[i][i]);
                // 左右
                int p5 = Math.min(l2 + r2 + f2[i][j], l5 + r5 + f5[i][i]);
                // 上下
                int p6 = Math.min(u2 + d2 + f2[i][j], u5 + d5 + f5[i][i]);
                ans = Math.max(ans, Math.max(Math.max(p1, p2), Math.max(Math.max(p3, p4), Math.max(p5, p6))));
            }
        }
        return ans;
    }

    public static int factors(int num, int fac) {
        int ans = 0;
        while (num % fac == 0) {
            ans++;
            num /= fac;
        }
        return ans;
    }
}
