package week.week_2023_07_04;

/**
 * @author Mr.Du
 * @ClassName Code_05_LeetCode_879
 * @date 2023年07月27日
 */
// https://leetcode.cn/problems/profitable-schemes/
public class Code_05_LeetCode_879 {
    // 尝试
    // n : 员工的额度 ， 不能超
    // p : 利润的额度 ， 不能少
    // group[i] : 需要几个人
    // profit[i] : 产生的利润
    public int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {
        return process1(group, profit, 0, n, minProfit);
    }

    // 来到i号项目
    // 还剩下r个员工
    // 利润还有s才能达成目标 s<=0 就算完成目标了
    // 返回方案
    public int process1(int[] g, int[] p, int i, int r, int s) {
        if (r <= 0) { // 没有员工了
            return s <= 0 ? 1 : 0;
        }
        // 项目没了
        if (i == g.length) {
            return s <= 0 ? 1 : 0;
        }
        int p1 = process1(g, p, i + 1, r, s);
        int p2 = 0;
        // 当前项目所需的人员不超过
        if (g[i] <= r) {
            p2 = process1(g, p, i + 1, r - g[i], s - p[i]);
        }
        return p1 + p2;
    }

    int mod = 1_000_000_007;

    // 挂一个缓存
    public int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;
        int[][][] dp = new int[m][n + 1][minProfit + 1];
        for (int a = 0; a < m; a++) {
            for (int b = 0; b <= n; b++) {
                for (int c = 0; c <= minProfit; c++) {
                    dp[a][b][c] = -1;
                }
            }
        }
        return process2(group, profit, 0, n, minProfit, dp);
    }

    // 来到i号项目
    // 还剩下r个员工
    // 利润还有s才能达成目标 s<=0 就算完成目标了
    // 返回方案
    public int process2(int[] g, int[] p, int i, int r, int s, int[][][] dp) {
        if (r <= 0) { // 没有员工了
            return s <= 0 ? 1 : 0;
        }
        // 项目没了
        if (i == g.length) {
            return s <= 0 ? 1 : 0;
        }
        if (dp[i][r][s] != -1) {
            return dp[i][r][s];
        }
        int p1 = process2(g, p, i + 1, r, s, dp);
        int p2 = 0;
        // 当前项目所需的人员不超过
        if (g[i] <= r) {
            p2 = process2(g, p, i + 1, r - g[i], Math.max(0, s - p[i]), dp);
        }
        int ans = (p1 + p2) % mod;
        dp[i][r][s] = ans;
        return ans;
    }

    public int profitableSchemes3(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; // 剩余需要搞定的利润为0，则方法有效
        }
        int m = group.length;
        // 当前第i层都依赖i+1层
        for (int i = m - 1; i >= 0; i--) {
            // 当前第r行依赖本行或者r-x行
            for (int r = n; r >= 0; r--) {
                for (int s = minProfit; s >= 0; s--) {
                    int p1 = dp[r][s];
                    int p2 = group[i] <= r ? dp[r - group[i]][Math.max(0, s - profit[i])] : 0;
                    dp[r][s] = (p1 + p2) % mod;
                }
            }
        }
        return dp[n][minProfit];
    }

}
