package hope.class81_status2;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code02_OptimalAccountBalancing
 * @date 2024年04月16日 下午 03:57
 */
// 最优账单平衡
// 给你一个表示交易的数组 transactions
// 其中 transactions[i] = [fromi, toi, amounti]
// 表示 ID = fromi 的人给 ID = toi 的人共计 amounti
// 请你计算并返回还清所有债务的最小交易笔数
// 测试链接 : https://leetcode.cn/problems/optimal-account-balancing/
public class Code02_OptimalAccountBalancing {
    // 题目说了人员编号的最大范围：0 ~ 12
    public static int MAXN = 13;

    public static int minTransfers(int[][] transactions) {
        int[] debt = debts(transactions);
        int n = debt.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        return n - f(debt, (1 << n) - 1, 0, n, dp);
    }

    public static int f(int[] debt, int set, int sum, int n, int[] dp) {
        if (dp[set] != -1) {
            return dp[set];
        }
        int ans = 0;
        // 集合中还有元素
        if ((set & (set - 1)) != 0) {
            if (sum == 0) {
                for (int i = 0; i < n; i++) {
                    if ((set & (1 << i)) != 0) {
                        ans = f(debt, set ^ (1 << i), sum - debt[i], n, dp) + 1;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < n; i++) {
                    if ((set & (1 << i)) != 0) {
                        ans = Math.max(ans, f(debt, set ^ (1 << i), sum - debt[i], n, dp));
                    }
                }
            }
        }
        dp[set] = ans;
        return ans;
    }

    public static int[] debts(int[][] transactions) {
        int[] help = new int[MAXN];
        for (int[] cur : transactions) {
            help[cur[0]] -= cur[2];
            help[cur[1]] += cur[2];
        }
        int n = 0;
        for (int num : help) {
            if (num != 0) {
                n++;
            }
        }
        int[] debt = new int[n];
        int index = 0;
        for (int num : help) {
            if (num != 0) {
                debt[index++] = num;
            }
        }
        return debt;
    }
}
