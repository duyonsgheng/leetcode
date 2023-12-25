package hope.dp_3;

/**
 * @author Mr.Du
 * @ClassName Code05_MinimumDeleteBecomeSubstring
 * @date 2023年12月22日 14:50
 */

// 删除至少几个字符可以变成另一个字符串的子串
// 给定两个字符串s1和s2
// 返回s1至少删除多少字符可以成为s2的子串
// 对数器验证
public class Code05_MinimumDeleteBecomeSubstring {
    public static int minDelete(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        // dp[i][j]
        // s1[前缀长度为i]至少删除几个可以变为s2[前缀长度为j]的任意后缀串
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
            for (int j = 1; j <= m; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= m; i++) {
            ans = Math.min(ans, dp[n][i]);
        }
        return ans;
    }
}
