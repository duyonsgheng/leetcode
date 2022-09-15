package week.week_2022_04_01;

/**
 * @ClassName Code_04_MaxScoreMoveInBoard
 * @Author Duys
 * @Description
 * @Date 2022/4/7 10:41
 **/

// 来自小红书
// 小红书第一题：
// 薯队长从北向南穿过一片红薯地（南北长M，东西宽N），红薯地被划分为1x1的方格，
// 他可以从北边的任何一个格子出发，到达南边的任何一个格子，
// 但每一步只能走到东南、正南、西南方向的三个格子之一，
// 而且不能跨出红薯地，他可以获得经过的格子上的所有红薯，请问他可以获得最多的红薯个数。
public class Code_04_MaxScoreMoveInBoard {

    public static int maxScore(int[][] map) {
        if (map == null || map.length == 0) {
            return 0;
        }
        int n = map.length;
        int m = map[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -2;
            }
        }
        int ans = 0;
        // 从每一个北边的点触发
        for (int i = 0; i < n; i++) {

        }
        return ans;
    }

    public static int process(int[][] map, int row, int col, int[][] dp) {
        if (col < 0 || col == map[0].length) {
            return -1;
        }
        if (dp[row][col] != -2) {
            return dp[row][col];
        }
        //
        int ans = 0;
        if (row == map.length - 1) {
            ans = map[row][col];
        } else {
            // 东南、正南、西南
            int cur = map[row][col];
            int p1 = process(map, row + 1, col - 1, dp);
            int p2 = process(map, row + 1, col, dp);
            int p3 = process(map, row + 1, col + 1, dp);
            ans = cur + Math.max(p1, Math.max(p2, p3));
        }
        dp[row][col] = ans;
        return ans;
    }
}
