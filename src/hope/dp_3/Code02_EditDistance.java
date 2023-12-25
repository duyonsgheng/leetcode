package hope.dp_3;

/**
 * @author Mr.Du
 * @ClassName Code02_EditDistance
 * @date 2023年12月19日 17:38
 */
// 编辑距离
// 给你两个单词 word1 和 word2
// 请返回将 word1 转换成 word2 所使用的最少代价
// 你可以对一个单词进行如下三种操作：
// 插入一个字符，代价a
// 删除一个字符，代价b
// 替换一个字符，代价c
// 测试链接 : https://leetcode.cn/problems/edit-distance/
public class Code02_EditDistance {

    public int minDistance(String word1, String word2) {
        return editDistance1(word1, word2, 1, 1, 1);
    }

    // a再str1中插入一个字符的代价
    // b再str1中删除一个字符的代价
    // c再str1中修改一个字符的代价
    public static int editDistance1(String str1, String str2, int a, int b, int c) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        int[][] dp = new int[n + 1][m + 1];
        // dp[i][j]
        // s1[前缀长度为i]想变成s2[前缀长度为j]需要的代价
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i * b;
        }
        for (int i = 1; i <= m; i++) {
            dp[0][i] = i * a;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int p1 = Integer.MAX_VALUE;
                // 相等
                if (s1[i - 1] == s2[j - 1]) {
                    p1 = dp[i - 1][j - 1];
                }
                int p2 = Integer.MAX_VALUE;
                // 不等，但是修改
                if (s1[i - 1] != s2[j - 1]) {
                    p2 = dp[i - 1][j - 1] + c;
                }
                // 插入一个
                int p3 = dp[i][j - 1] + a;
                // 删除一个
                int p4 = dp[i - 1][j] + b;
                dp[i][j] = Math.min(Math.min(p1, p2), Math.min(p3, p4));
            }
        }
        return dp[n][m];
    }

    public static int editDistance2(String str1, String str2, int a, int b, int c) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        int[][] dp = new int[n + 1][m + 1];
        // 从str1 到 str2的空
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i * b;
        }
        // 从空的str1 到 str2
        for (int i = 1; i <= m; i++) {
            dp[0][i] = i * a;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + b, dp[i][j - 1] + a), dp[i - 1][j - 1] + c);
                }
            }
        }
        return dp[n][m];
    }
}
