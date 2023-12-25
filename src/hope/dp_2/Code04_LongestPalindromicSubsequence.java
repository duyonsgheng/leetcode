package hope.dp_2;

/**
 * @author Mr.Du
 * @ClassName Code04_LongestPalindromicSubsequence
 * @date 2023年12月18日 15:03
 */
// 最长回文子序列
// 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度
// 测试链接 : https://leetcode.cn/problems/longest-palindromic-subsequence/
public class Code04_LongestPalindromicSubsequence {
    // 最长回文的问题可以转化成最长公共子序列问题
    public static int longestPalindromeSubseq1(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        return f1(s, 0, n - 1);
    }

    public static int f1(char[] arr, int l, int r) {
        if (l == r) {
            return 1;
        }
        if (l + 1 == r) {
            return arr[l] == arr[r] ? 2 : 1;
        }
        if (arr[l] == arr[r]) {
            return 2 + f1(arr, l + 1, r - 1);
        } else {
            return Math.max(f1(arr, l + 1, r), f1(arr, l, r - 1));
        }
    }

    // 只需要填二维网格
    public static int longestPalindromeSubseq2(String str) {
        char[] arr = str.toCharArray();
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int l = n - 1; l >= 0; l--) {
            dp[l][l] = 1;
            if (l + 1 < n) {
                dp[l][l + 1] = arr[l] == arr[l + 1] ? 2 : 1;
            }
            for (int r = l + 2; r < n; r++) {
                if (arr[l] == arr[r]) {
                    dp[l][r] = 2 + dp[l - 1][r - 1];
                } else {
                    dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
