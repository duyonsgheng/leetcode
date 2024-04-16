package hope.class80_status2;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName Code01_NumberOfWaysWearDifferentHats
 * @date 2024年04月16日 下午 03:27
 */

// 每个人戴不同帽子的方案数
// 总共有 n 个人和 40 种不同的帽子，帽子编号从 1 到 40
// 给你一个整数列表的列表 hats ，其中 hats[i] 是第 i 个人所有喜欢帽子的列表
// 请你给每个人安排一顶他喜欢的帽子，确保每个人戴的帽子跟别人都不一样，并返回方案数
// 由于答案可能很大，请返回它对10^9+7取余后的结果
// 测试链接 : https://leetcode.cn/problems/number-of-ways-to-wear-different-hats-to-each-other
public class Code01_NumberOfWaysWearDifferentHats {
    public static int MOD = 1000000007;

    public static int numberWays(List<List<Integer>> arr) {
        int m = 0; // 帽子颜色的最大值
        for (List<Integer> person : arr) {
            for (int hat : person) {
                m = Math.max(m, hat);
            }
        }
        int n = arr.size();
        // 1~m帽子，能满足那些人，状态信息
        int[] hats = new int[m + 1];
        for (int pi = 0; pi < n; pi++) {
            for (int hat : arr.get(pi)) {
                hats[hat] |= 1 << pi;
            }
        }
        int[][] dp = new int[m + 1][1 << n];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return f2(hats, m, n, 1, 0, dp);
    }

    // m 帽子最大颜色，1~m
    // n 人的数量 0~n-1
    // i 来到什么颜色的帽子
    // s n个人，谁满足了状态就是1，不满足就是0
    public static int f1(int[] hats, int m, int n, int i, int s, int[][] dp) {
        if (s == (1 << n) - 1) {
            return 1; // 都满足了
        }
        // 颜色都选完了。还没满足
        if (i == m + 1) {
            return 0;
        }
        if (dp[i][s] != -1) {
            return dp[i][s];
        }
        // p1:i颜色的帽子不分配给任何人
        int ans = f1(hats, m, n, i + 1, s, dp);
        // p2:i颜色的帽子分配给可能得每一个人
        int cur = hats[i];
        // 枚举每一个人
        for (int p = 0; p < n; p++) {
            // 先看看当前人是不是有这个颜色的帽子
            // 然后看看是不是选择过了
            if ((cur & (1 << p)) != 0 && (s & (1 << p)) == 0) {
                ans = (ans + f1(hats, m, n, i + 1, s | (1 << p), dp)) % MOD;
            }
        }
        dp[i][s] = ans;
        return ans;
    }

    public static int f2(int[] hats, int m, int n, int i, int s, int[][] dp) {
        if (s == (1 << n) - 1) {
            return 1; // 都满足了
        }
        // 颜色都选完了。还没满足
        if (i == m + 1) {
            return 0;
        }
        if (dp[i][s] != -1) {
            return dp[i][s];
        }
        // p1:i颜色的帽子不分配给任何人
        int ans = f1(hats, m, n, i + 1, s, dp);
        // p2:i颜色的帽子分配给可能得每一个人
        int cur = hats[i];
        // 枚举每一个人
        // 提取最右侧的1
        int rightOne = 0;
        while (cur != 0) { // 最右侧的1，就是当前帽子能满足那些人。依次尝试人就可以了
            rightOne = cur & -cur;
            // 说明之前没选择过
            if ((s & rightOne) == 0) {
                ans = (ans + f2(hats, m, n, i + 1, s | rightOne, dp)) % MOD;
            }
            cur ^= rightOne; // 用过了的位置就不要再用了
        }
        dp[i][s] = ans;
        return ans;
    }
}
