package custom.code_2022_06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_115
 * @Author Duys
 * @Description
 * @Date 2022/6/14 13:22
 **/
// 115. 不同的子序列
// 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
//字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是）
//题目数据保证答案符合 32 位带符号整数范围。
//链接：https://leetcode.cn/problems/distinct-subsequences
public class LeetCode_115 {
    public int numDistinct(String s, String t) {
        if (s == null || s.length() <= 0 || t == null) {
            return 0;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int n = str1.length;
        int m = str2.length;
        if (n < m) {
            return 0;
        }
        //dp[i][j] str1 0...i 和 str2 0...j 有多少匹配的
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][m] = 1;// 目标一个也不选。
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + (str1[i] == str2[j] ? dp[i + 1][j + 1] : 0);
            }
        }
        return dp[0][0];
    }
}
