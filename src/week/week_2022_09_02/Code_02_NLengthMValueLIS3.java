package week.week_2022_09_02;

/**
 * @ClassName Code_02_NLengthMValueLIS3
 * @Author Duys
 * @Description
 * @Date 2022/9/15 12:40
 **/
// 来自微众银行
// 给定一个数字n，代表数组的长度
// 给定一个数字m，代表数组每个位置都可以在1~m之间选择数字
// 所有长度为n的数组中，最长递增子序列长度为3的数组，叫做达标数组
// 返回达标数组的数量
// 1 <= n <= 500
// 1 <= m <= 10
// 500 * 10 * 10 * 10
// 结果对998244353取模
// 实现的时候没有取模的逻辑，因为非重点
public class Code_02_NLengthMValueLIS3 {
    /**
     * 思路：
     * 1.最长递增子数组问题：经典的ends数组
     * 2.当前题目使用ends数组的结论：最小结尾是啥，之前求得最长递增子序列的时候还会使用二分来找。如果小于等于之前结果，就更新。
     */

    public static int number(int n, int m) {
        int[][][][] dp = new int[n][m + 1][m + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int f = 0; f <= m; f++) {
                for (int s = 0; s <= m; s++) {
                    for (int t = 0; t <= m; t++) {
                        dp[i][f][s][t] = -1;
                    }
                }
            }
        }
        return process(0, 0, 0, 0, n, m, dp);
    }

    // i - 当前来到的数组下标
    //f- ends数组中的第一个位置
    //s- ends数组中的第二个位置
    //t- ends数组中的第三个位置
    public static int process(int i, int f, int s, int t, int n, int m, int[][][][] dp) {
        if (i == n) {
            return f != 0 && s != 0 && t != 0 ? 1 : 0;
        }
        if (dp[i][f][s][t] != -1) {
            return dp[i][f][s][t];
        }
        int ans = 0;
        // 每一个数字都可以在1到m中间选择
        for (int cur = 1; cur <= m; cur++) {
            // ends数组的特性，只要当前数小于等于之前的结尾，就可以更新
            if (f == 0 || cur <= f) {
                ans += process(i + 1, cur, s, t, n, m, dp);
            } else if (s == 0 || cur <= s) {
                ans += process(i + 1, f, cur, t, n, m, dp);
            } else if (t == 0 || cur <= t) {
                ans += process(i + 1, f, s, cur, n, m, dp);
            } else {
                // 啥也不做，已经不满足了，
            }
        }
        dp[i][f][s][t] = ans;
        return ans;
    }
}
