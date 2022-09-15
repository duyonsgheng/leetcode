package custom.code_2022_05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_72
 * @Author Duys
 * @Description
 * @Date 2022/5/10 14:28
 **/
// 72.编辑距离
//给你两个单词word1 和word2， 请返回将word1转换成word2 所使用的最少操作数 。
//你可以对一个单词进行如下三种操作：
//插入一个字符
//删除一个字符
//替换一个字符
//链接：https://leetcode.cn/problems/edit-distance
public class LeetCode_72 {
    // horse - > ros
    public static int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() <= 0) {
            return word2 == null ? 0 : word2.length();
        }
        if (word2 == null || word2.length() <= 0) {
            return word1 == null ? 0 : word1.length();
        }
        word1 = " " + word1;
        word2 = " " + word2;
        /*if (n * m == 0) {
            return n + m;
        }*/
        // 要把 str1 变成 str2
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        int n = str1.length;
        int m = str2.length;

        // s1: a b e c g f d
        // s2: b g d
        // 我们在s1中删除和在s2中插入其实等价
        // 那么何不我们就在s1中替换和添加呢
        // 那么dp转移方程就很好处理了来到s1 的i位置 s2的j位置，如果i和j相等
        // 通式1. s1：0~i s2：0~j-1 距离是 a 那么 s1：0~i s2：0~j 不会超过 a+1，我们在s2中添加一个字符，就可以了
        // 通式1. s1：0~i-1 s2：0~j 距离是 a 那么 s1：0~i s2：0~j 不会超过 a+1，我们在s1中添加一个字符，就可以了
        // 情况1：s1[i]==s2[j] 那么 dp[i][j] = dp[i-1][j-1]
        // 情况2：s1[i]!=s2[j] 那么 dp[i][j] = dp[i-1][j]+1 ,dp[i][j-1]+1 ,dp[i-1][j-1]
        int[][] dp = new int[n + 1][m + 1];
        // 边界处理, i次删除
        //dp[0][0] = str1[0] == str2[0] ? 0 : 1;
        for (int i = 0; i < n; i++) {
            dp[i][0] = i;//dp[i - 1][0] + (str1[i] == str2[0] ? 0 : 1);
        }
        // 边界处理，i次添加
        for (int i = 0; i < m; i++) {
            dp[0][i] = i;//dp[0][i - 1] + (str2[i] == str1[0] ? 0 : 1);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (str1[i] == str2[j]) {
                    // 相等的话，意思就是
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        // "pneumonoultramicroscopicsilicovolcanoconiosis"
        //"ultramicroscopically"
        System.out.println(minDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically"));

        System.out.println(minDistance("horse", "ros"));
    }
}
