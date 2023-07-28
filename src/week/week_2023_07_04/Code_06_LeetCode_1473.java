package week.week_2023_07_04;

/**
 * @author Mr.Du
 * @ClassName Code_06_LeetCode_1473
 * @date 2023年07月27日
 */
public class Code_06_LeetCode_1473 {
    // 尝试
    public int minCost(int[] houses,// 每个房子固有的颜色，0 ：去涂，>0: 不能改的
                       int[][] cost,// cost[i][j] : i号房子，涂上j这个颜色，花费多少
                       int m,       // 房子数量
                       int n,       // 颜色数量
                       int target) {// 恰好组成 target 个街区
        int[][][] dp = new int[m][target + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k <= target; k++) {
                for (int c = 0; c <= n; c++) {
                    dp[i][k][c] = -1;
                }
            }
        }
        int ans = process1(houses, cost, n, 0, target, 0, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 返回Integer.MAX_VALUE：做不到
    // 返回正常值，代表最小花费
    public int process1(int[] h, int[][] c, int n,// 颜色总数。1~n固定参数
                        int i, // 来到的房子编号
                        int k, // 剩余的房子必须要整出k个街区来
                        int p, // 上一个房子的颜色
                        int[][][] dp) {
        if (k < 0) { // 街区数没了，搞不定了
            return Integer.MAX_VALUE;
        }
        // 来到最后的房子，如果街区还有剩下的，已经搞不定了
        if (i == h.length) {
            return k == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (dp[i][k][p] != -1) {
            return dp[i][k][p];
        }
        int ans = Integer.MAX_VALUE;
        // 已经有颜色了
        if (h[i] != 0) {
            if (h[i] != p) { // 和上一个位置的房子颜色不一样
                ans = process1(h, c, n, i + 1, k - 1, h[i], dp);
            } else { // 和上一个位置的房子颜色一样
                ans = process1(h, c, n, i + 1, k, h[i], dp);
            }
        } else { // 没有颜色
            // 枚举每一种颜色
            for (int fill = 1, next; i <= n; i++) {
                if (fill == p) {
                    next = process1(h, c, n, i + 1, k, fill, dp);
                } else {
                    next = process1(h, c, n, i + 1, k - 1, fill, dp);
                }
                if (next != Integer.MAX_VALUE) {
                    ans = Math.min(ans, c[i][fill - 1] + next);
                }
            }
        }
        dp[i][k][p] = ans;
        return ans;
    }

    // 上面的尝试方法我们观察位置，可以轻易的变为二维dp，但是枚举要省去的话，需要用辅助结构来简化枚举行为
    public static int minCost3(int[] houses, int[][] cost, int m, int n, int target) {
        // 街区，和颜色
        int[][] dp = new int[target + 1][n + 1];
        for (int c = 0; c <= n; c++) {
            dp[0][c] = 0;
        }
        for (int k = 1; k <= target; k++) {
            for (int c = 0; c <= n; c++) {
                dp[k][c] = Integer.MAX_VALUE;
            }
        }
        // 上一行的值
        int[] memo = new int[n + 1];
        // 上一行左侧的最小
        int[] minl = new int[n + 2];
        // 上一行右侧的最小
        int[] minr = new int[n + 2];
        minr[0] = minl[0] = minl[n + 1] = minr[n + 1] = Integer.MAX_VALUE;
        for (int i = m - 1; i >= 0; i--) {
            if (houses[i] != 0) {
                for (int k = target, memory; k >= 0; k--) {
                    memory = dp[k][houses[i]];
                    for (int c = 0; c <= n; c++) {
                        if (houses[i] != c) {
                            dp[k][c] = k == 0 ? Integer.MAX_VALUE : dp[k + 1][houses[i]];
                        } else {
                            dp[k][c] = memory;
                        }
                    }
                }
            } else {
                for (int k = target; k >= 0; k--) {
                    for (int c = 0; c <= n; c++) {
                        memo[c] = dp[k][c]; // 上一行相同位置的值
                    }
                    // 更新辅助结构
                    for (int fill = 1; fill <= n; fill++) {
                        if (k == 0 || dp[k - 1][fill] == Integer.MAX_VALUE) {
                            minl[fill] = minl[fill - 1];
                        } else {
                            minl[fill] = Math.min(minl[fill - 1], cost[i][fill - 1] + dp[k - 1][fill]);
                        }
                    }
                    for (int fill = n; fill >= 1; fill--) {
                        if (k == 0 || dp[k - 1][fill] == Integer.MAX_VALUE) {
                            minr[fill] = minr[fill + 1];
                        } else {
                            minr[fill] = Math.min(minr[fill + 1], cost[i][fill - 1] + dp[k - 1][fill]);
                        }
                    }
                    for (int c = 0, ans; c <= n; c++) {
                        if (c == 0 || memo[c] == Integer.MAX_VALUE) {
                            ans = Integer.MAX_VALUE;
                        } else {
                            ans = cost[i][c - 1] + memo[c];
                        }
                        if (c > 0) {
                            ans = Math.min(ans, minl[c - 1]);
                        }
                        if (c < n) {
                            ans = Math.min(ans, minr[c + 1]);
                        }
                        dp[k][c] = ans;
                    }
                }
            }
        }
        return dp[target][0] != Integer.MAX_VALUE ? dp[target][0] : -1;
    }
}
