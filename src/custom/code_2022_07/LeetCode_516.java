package custom.code_2022_07;

/**
 * @ClassName LeetCode_516
 * @Author Duys
 * @Description
 * @Date 2022/7/6 17:50
 **/
// 516. 最长回文子序列
// 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
//子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
//链接：https://leetcode.cn/problems/longest-palindromic-subsequence
public class LeetCode_516 {

    // 先来一个暴力解答
    public int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process1(str, 0, str.length - 1);
    }

    // 返回当前 l..r 上最长回文子序列
    public static int process1(char[] str, int l, int r) {
        if (l == r) { // 解决奇数长度，只有一个字符
            return 1;
        }
        // 解决偶数长度问题
        if (l == r - 1) {
            return str[l] == str[r] ? 2 : 1;
        }
        // l 和 r 位置的我都不要
        int p1 = process1(str, l + 1, r - 1);
        // 要l位置，不要r位置
        int p2 = process1(str, l, r - 1);
        // 要r位置不要l位置
        int p3 = process1(str, l + 1, r);
        //l r 都要
        int p4 = str[l] == str[r] ? (2 + process1(str, l + 1, r - 1)) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    // 两个可变参数-改动态规划
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n + 1][n + 1];
        dp[n - 1][n - 1] = 1;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                if (str[l] == str[r]) {
                    dp[l][r] = Math.max(dp[l][r], 2 + dp[l + 1][r - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
