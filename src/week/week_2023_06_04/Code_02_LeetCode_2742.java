package week.week_2023_06_04;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code_02_LeetCode_2742
 * @date 2023年06月29日
 */
// 2742. 给墙壁刷油漆
// https://leetcode.cn/problems/painting-the-walls/
public class Code_02_LeetCode_2742 {
    // 本题看起来简单，其实这个递归怎么写就很麻烦了，看起来是一个背包问题
    public int paintWalls1(int[] cost, int[] time) {
        int n = cost.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process1(cost, time, 0, n, dp);
    }

    // 当前来到cur
    // 还有rest墙需要刷
    // 返回最少得代价
    public int process1(int[] cost, int[] time, int cur, int rest, int[][] dp) {
        // 没有墙了，不需要代价了
        if (rest <= 0) {
            return 0;
        }
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans;
        // 都到越界位置了，rest还大于0，说明搞不定了
        if (cur == cost.length) {
            ans = Integer.MAX_VALUE;
        } else {
            // 1.当前墙不用付费
            int p1 = process1(cost, time, cur + 1, rest, dp);
            // 2.当前墙付费
            int p2 = Integer.MAX_VALUE;
            // 这里剩下的墙，我们当前使用了付费的，那么在付费刷的这期间，可以使用不付费的刷 time[cur]面墙
            int next2 = process1(cost, time, cur + 1, rest - 1 - time[cur], dp);
            if (next2 != Integer.MAX_VALUE) {
                p2 = cost[cur] + next2;
            }
            ans = Math.min(p1, p2);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp, Integer.MAX_VALUE);
        }
        dp[0] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = n; rest >= 1; rest--) {
                if (rest - 1 - time[i] <= 0) {
                    dp[rest] = Math.min(dp[rest], cost[i]);
                } else if (dp[rest - 1 - time[i]] != Integer.MAX_VALUE) {
                    dp[rest] = Math.min(dp[rest], cost[i] + dp[rest - 1 - time[i]]);
                }
            }
        }
        return dp[n];
    }
}
