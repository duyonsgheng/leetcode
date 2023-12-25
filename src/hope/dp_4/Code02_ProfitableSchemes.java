package hope.dp_4;

/**
 * @author Mr.Du
 * @ClassName Code02_ProfitableSchemes
 * @date 2023年12月22日 15:19
 */
// 盈利计划(多维费用背包)
// 集团里有 n 名员工，他们可以完成各种各样的工作创造利润
// 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与
// 如果成员参与了其中一项工作，就不能参与另一项工作
// 工作的任何至少产生 minProfit 利润的子集称为 盈利计划
// 并且工作的成员总数最多为 n
// 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
// 测试链接 : https://leetcode.cn/problems/profitable-schemes/
public class Code02_ProfitableSchemes {
    // n : 员工的额度，不能超
    // p : 利润的额度，不能少
    // group[i] : i号项目需要几个人
    // profit[i] : i号项目产生的利润
    // 返回能做到员工不能超过n，利润不能少于p的计划有多少个
    public static int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {
        return f1(group, profit, 0, n, minProfit);
    }

    //i:来到i号工作
    //r:员工额度还有r人
    //s:利润还有s才达标
    public static int f1(int[] g, int[] p, int i, int r, int s) {
        // 没人了
        if (r <= 0) {
            return s <= 0 ? 1 : 0;
        }
        // 没有可用的工作了
        if (i == g.length) {
            return s <= 0 ? 1 : 0;
        }
        // 1.不要当前工作
        int p1 = f1(g, p, i + 1, r, s);
        int p2 = 0;
        if (g[i] <= r) {
            p2 = f1(g, p, i + 1, r - g[i], s - p[i]);
        }
        return p1 + p2;
    }

    public static int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        int mod = 1_000_000_007;
        for (int r = 0; r <= n; r++) {
            dp[r][0] = 1;
        }
        int m = group.length;
        for (int i = m - 1; i >= 0; i--) {
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
