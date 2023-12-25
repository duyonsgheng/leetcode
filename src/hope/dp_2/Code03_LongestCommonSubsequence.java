package hope.dp_2;

/**
 * @author Mr.Du
 * @ClassName Code03_LongestCommonSubsequence
 * @date 2023年12月11日 10:59
 */
// 最长公共子序列
// 给定两个字符串text1和text2
// 返回这两个字符串的最长 公共子序列 的长度
// 如果不存在公共子序列，返回0
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列
// 测试链接 : https://leetcode.cn/problems/longest-common-subsequence/
public class Code03_LongestCommonSubsequence {
    // 样本对应模型
    // 一半是讨论结尾位置如何如何
    public static int longestCommonSubsequence1(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        return f1(s1, s2, n - 1, m - 1);
    }

    // s1[0....i1]与s2[0...i2]的最长公共子序列长度
    public static int f1(char[] s1, char[] s2, int i1, int i2) {
        if (i1 < 0 || i2 < 0) {
            return 0;
        }
        int p1 = f1(s1, s2, i1 - 1, i2);
        int p2 = f1(s1, s2, i1, i2 - 1);
        int p3 = f1(s1, s2, i1 - 1, i2 - 1);
        int p4 = s1[i1] == s2[i2] ? (p3 + 1) : 0;
        return Math.max(Math.max(p3, p4), Math.max(p1, p2));
    }

    // 不用下标来定义尝试，用长度来定义尝试
    public static int longestCommonSubsequence2(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        return f2(s1, s2, n, m);
    }

    //
    public static int f2(char[] s1, char[] s2, int l1, int l2) {
        if (l1 == 0 || l2 == 0) {
            return 0;
        }
        if (s1[l1 - 1] == s2[l2 - 1]) {
            return f2(s1, s2, l1 - 1, l2 - 1) + 1;
        } else {
            return Math.max(f2(s1, s2, l1 - 1, l2), f2(s1, s2, l1, l2 - 1));
        }
    }

    // 分析位置依赖
    public static int longestCommonSubsequence3(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int l1 = 1; l1 <= n; l1++) {
            for (int l2 = 1; l2 <= m; l2++) {
                if (s1[l1 - 1] == s2[l2 - 1]) {
                    dp[l1][l2] = dp[l1 - 1][l2 - 1] + 1;
                } else {
                    dp[l1][l2] = Math.max(dp[l1 - 1][l2], dp[l1][l2 - 1]);
                }
            }
        }
        return dp[n][m];
    }

    // 空间压缩，每个格子依赖，左边，上边，左上
    public static int longestCommonSubsequence4(String str1, String str2) {
        char[] s1; // 长的
        char[] s2; // 短的
        if (str1.length() >= str2.length()) {
            s1 = str1.toCharArray();
            s2 = str2.toCharArray();
        } else {
            s1 = str2.toCharArray();
            s2 = str1.toCharArray();
        }
        int n = s1.length;
        int m = s2.length;
        int[] dp = new int[m + 1];
        for (int l1 = 1; l1 <= n; l1++) {
            int leftUp = 0, backUp = 0;
            for (int l2 = 1; l2 <= m; l2++) {
                backUp = dp[l2];
                if (s1[l1 - 1] == s2[l2 - 1]) {
                    dp[l2] = leftUp + 1;
                } else {
                    dp[l2] = Math.max(dp[l2], dp[l2 - 1]);
                }
                leftUp = backUp;
            }
        }
        return dp[m];
    }
}
