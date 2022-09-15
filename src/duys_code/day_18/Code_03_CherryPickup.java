package duys_code.day_18;

import java.util.Scanner;

/**
 * @ClassName Code_03_CherryPickup
 * @Author Duys
 * @Description https://www.nowcoder.com/questionTerminal/8ecfe02124674e908b2aae65aad4efdf
 * @Date 2021/11/2 10:55
 **/
public class Code_03_CherryPickup {
    /**
     * 题意就是：
     * 一个二维矩阵，一个人开始从左上角走到右下角，然后从右下角走到左上角，最大的累加和
     * 但是第一趟走过的路，如果第二趟重复走，将不会得到任何得分
     */
    /**
     * 思路：
     * 我们假设两个人同时从左上角到右下角。如果同时进入了一个格子，只得一分。求两个人同时走最大得得分
     * 因为两个人同时出发，而且只能向右或者向下走。那么一定是一同走到右下角位置
     */
    public static int cherryPickup(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        return process1(grid, 0, 0, 0, 0);
    }

    public static int process1(int[][] grid, int x1, int y1, int x2, int y2) {
        int n = grid.length;
        int m = grid[0].length;
        // 同时进入右下角
        if (x1 == n - 1 && y1 == m - 1) {
            return grid[x1][y1];
        }
        // A右 B右
        // A右 B下
        // A下 B下
        // A下 B右
        int best = 0;
        // a往右走
        if (x1 < m - 1) {
            // b往右
            if (x2 < m - 1) {
                best = Math.max(best, process1(grid, x1 + 1, y1, x2 + 1, y2));
            }
            // b往下
            if (y2 < n - 1) {
                best = Math.max(best, process1(grid, x1 + 1, y1, x2, y2 + 1));
            }
        }
        // a往下走
        if (y1 < n - 1) {
            if (x2 < m - 1) {
                best = Math.max(best, process1(grid, x1, y1 + 1, x2 + 1, y2));
            }
            if (y2 < n - 1) {
                best = Math.max(best, process1(grid, x1, y1 + 1, x2, y2 + 1));
            }
        }
        int cur = 0;
        if (x1 == x2 && y1 == y2) {
            cur = grid[x1][y1];
        } else {
            cur = grid[x1][y1] + grid[x2][y2];
        }
        return cur + best;
    }

    public static int cherryPickup2(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int[][][] dp = new int[N][M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        int ans = process2(grid, 0, 0, 0, dp);
        return ans < 0 ? 0 : ans;
    }

    // 减少参数得记忆化搜索
    // 同时走，而且只能向右或者向下，那么 x1+y1 = x2+y2
    public static int process2(int[][] m, int x1, int y1, int x2, int[][][] dp) {
        if (x1 == m.length || y1 == m[0].length || x2 == m.length || x1 + y1 - x2 == m[0].length) {
            return Integer.MIN_VALUE;
        }
        if (dp[x1][y1][x2] != Integer.MIN_VALUE) {
            return dp[x1][y1][x2];
        }
        if (x1 == m.length - 1 && y1 == m[0].length - 1) {
            dp[x1][y1][x2] = m[x1][y1];
            return dp[x1][y1][x2];
        }
        int next = Integer.MIN_VALUE;
        next = Math.max(next, process2(m, x1 + 1, y1, x2 + 1, dp));
        next = Math.max(next, process2(m, x1 + 1, y1, x2, dp));
        next = Math.max(next, process2(m, x1, y1 + 1, x2, dp));
        next = Math.max(next, process2(m, x1, y1 + 1, x2 + 1, dp));
        // 判断一下，
        if (m[x1][y1] == -1 || m[x2][x1 + y1 - x2] == -1 || next == -1) {
            dp[x1][y1][x2] = -1;
            return dp[x1][y1][x2];
        }
        if (x1 == x2) {
            dp[x1][y1][x2] = m[x1][y1] + next;
            return dp[x1][y1][x2];
        }
        dp[x1][y1][x2] = m[x1][y1] + m[x2][x1 + y1 - x2] + next;
        return dp[x1][y1][x2];
    }

    // 牛客
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int ans = cherryPickup(matrix);
        System.out.println(ans);
        sc.close();
    }
}
