package hope.class76_rangeDP1;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code04_MinimumCostToCutAStick
 * @date 2024年03月13日 18:08
 */
// 切棍子的最小成本
// 有一根长度为n个单位的木棍，棍上从0到n标记了若干位置
// 给你一个整数数组cuts，其中cuts[i]表示你需要将棍子切开的位置
// 你可以按顺序完成切割，也可以根据需要更改切割的顺序
// 每次切割的成本都是当前要切割的棍子的长度，切棍子的总成本是历次切割成本的总和
// 对棍子进行切割将会把一根木棍分成两根较小的木棍
// 这两根木棍的长度和就是切割前木棍的长度
// 返回切棍子的最小总成本
// 测试链接 : https://leetcode.cn/problems/minimum-cost-to-cut-a-stick/
public class Code04_MinimumCostToCutAStick {
    // 暴力展开
    public static int minCost1(int n, int[] cuts) {
        int m = cuts.length;
        Arrays.sort(cuts);
        int[] arr = new int[m + 2];
        arr[0] = 0;
        arr[m + 1] = n;
        for (int i = 1; i <= m; i++) {
            arr[i] = cuts[i - 1];
        }
        int[][] dp = new int[m + 2][m + 2];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = -1;
            }
        }
        return f1(arr, 1, m, dp);
    }

    public static int f1(int[] arr, int l, int r, int[][] dp) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return arr[r + 1] - arr[l - 1];
        }
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        int ans = Integer.MAX_VALUE;
        for (int k = l; k <= r; k++) {
            ans = Math.min(ans, f1(arr, l, k - 1, dp) + f1(arr, k + 1, r, dp));
        }
        dp[l][r] = ans;
        return ans;
    }

    // 严格位置依赖
    public static int minCost(int n, int[] cuts) {
        int m = cuts.length;
        Arrays.sort(cuts);
        int[] arr = new int[m + 2];
        arr[0] = 0;
        arr[m + 1] = n;
        for (int i = 1; i <= m; i++) {
            arr[i] = cuts[i - 1];
        }
        int[][] dp = new int[m + 2][m + 2];
        for (int l = m - 1, next; l >= 1; l--) {
            next = Integer.MAX_VALUE;
            for (int r = l + 1; r <= m; r++) {
                for (int k = l; k <= r; k++) {
                    next = Math.min(dp[l][r], dp[l][k - 1] + dp[k + 1][r]);
                }
                dp[l][r] = next + arr[r + 1] - arr[l - 1];
            }
        }
        return dp[1][m];
    }
}
