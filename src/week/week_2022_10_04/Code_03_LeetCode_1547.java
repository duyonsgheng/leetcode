package week.week_2022_10_04;

import java.util.Arrays;

/**
 * @ClassName Code_03_MinimumCostToCutAStick
 * @Author Duys
 * @Description
 * @Date 2022/10/27 11:57
 **/
// 有一根长度为 n 个单位的木棍，棍上从 0 到 n 标记了若干位置
// 给你一个整数数组 cuts ，其中 cuts[i] 表示你需要将棍子切开的位置
// 你可以按顺序完成切割，也可以根据需要更改切割的顺序
// 每次切割的成本都是当前要切割的棍子的长度，切棍子的总成本是历次切割成本的总和
// 对棍子进行切割将会把一根木棍分成两根较小的木棍
// 这两根木棍的长度和就是切割前木棍的长度
// 返回切棍子的最小总成本
// 测试链接 : https://leetcode.cn/problems/minimum-cost-to-cut-a-stick/
public class Code_03_LeetCode_1547 {
    // 就枚举第一刀的位置
    // 给数组补上 0 和 n
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        Arrays.sort(cuts);
        int[] arr = new int[m + 2];
        for (int i = 1; i <= m; i++) {
            arr[i] = cuts[i - 1];
        }
        arr[m + 1] = n;
        int[][] dp = new int[m + 2][m + 2];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = -1;
            }
        }
        return process(arr, 1, m, dp);
    }

    // l到r这之间第一刀的位置
    public static int process(int[] arr, int l, int r, int[][] dp) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            // 这之间的长度
            return arr[r + 1] - arr[l - 1];
        }
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            ans = Math.min(ans, process(arr, l, i - 1, dp) + process(arr, i + 1, r, dp));
        }
        // 第一刀左右两边位置 + l到r这之间的距离
        ans += arr[r + 1] - arr[l - 1];
        dp[l][r] = ans;
        return ans;
    }
}
