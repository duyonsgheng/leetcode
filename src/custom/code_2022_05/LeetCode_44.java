package custom.code_2022_05;

import java.util.Arrays;

/**
 * @ClassName LeetCode_44
 * @Author Duys
 * @Description
 * @Date 2022/5/5 15:49
 **/
// 给定一个字符串(s) 和一个字符模式(p) ，实现一个支持'?'和'*'的通配符匹配。
//'?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
//两个字符串完全匹配才算匹配成功。
//链接：https://leetcode-cn.com/problems/wildcard-matching
public class LeetCode_44 {
    // 样本对应模型
    // 动态规划
    // dp[i][j] 含义是s的前i个字符和p的前j个字符能否匹配
    // 可能性分析
    // 1.如果p的j位置是小写字母，那么s的i也是相同的小写字符 dp[i][j]=dp[i-1][j-1] ^ s[i] == p[j]
    // 2.如果p的j位置是？号，dp[i][j] = dp[i-1][j-1]
    // 3.如果p的j位置是 * 号
    //  3.1 使用当前*号来改变成 dp[i][j] = dp[i-1][j]
    //  3.2 不使用这个*号     dp[i][j] = dp[i][j-1]

    public static boolean isMatch(String s, String p) {
        if (s == null || s == null) {
            return false;
        }
        if (p.equals("*")) {
            return true;
        }
        if (s.length() == 1 && p.equals('?')) {
            return true;
        }
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        // 空字符串
        dp[0][0] = true;
        // 当p是“”，搞不定任何的东西
        for (int i = 1; i <= n; i++) {
            dp[i][0] = false;
        }

        for (int i = 1; i <= m; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 三种情况
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m];
    }
}
