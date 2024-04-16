package hope.class77_rangeDP2;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code06_CountDifferentPalindromicSubsequences
 * @date 2024年03月24日 21:50
 */
// 统计不同回文子序列
// 给你一个字符串s，返回s中不同的非空回文子序列个数
// 由于答案可能很大，请你将答案对10^9+7取余后返回
// 测试链接 : https://leetcode.cn/problems/count-different-palindromic-subsequences/
public class Code06_CountDifferentPalindromicSubsequences {
    public static int countPalindromicSubsequences(String str) {
        int mod = 1_000_000_007;
        char[] arr = str.toCharArray();
        int n = arr.length;
        int[] last = new int[256];

        //left[i] ：i位置的左边和arr[i]字符相等且最近的位置在哪，不存在就是-1
        int[] left = new int[n];
        Arrays.fill(last, -1);
        for (int i = 0; i < n; i++) {
            left[i] = last[arr[i]];
            last[arr[i]] = i;
        }
        //right[i] ：i位置的右边和arr[i]字符相等且最近的位置在哪，不存在就是n
        int[] right = new int[n];
        Arrays.fill(last, n);
        for (int i = n - 1; i >= 0; i--) {
            right[i] = last[arr[i]];
            last[arr[i]] = i;
        }
        // dp[i][j]:i...j范围上有多少不同的回文子序列
        long[][] dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 2, l, r; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] != arr[j]) {
                    // 容斥原理，中间部分算重复了，需要减去
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1] + mod;
                } else {
                    l = right[i];
                    r = left[j];
                    if (l > r) { // 说明内部没有和 i j位置相同的字符了
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    } else if (l == r) { // 说明只有一个
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    } else { // 有多个
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[l + 1][r - 1] + mod;
                    }
                }
                dp[i][j] %= mod;
            }
        }
        return (int) dp[0][n - 1];
    }
}
