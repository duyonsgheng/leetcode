package hope.dp_3;

/**
 * @author Mr.Du
 * @ClassName Code01_DistinctSubsequences
 * @date 2023年12月19日 17:25
 */
// 不同的子序列
// 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数
// 测试链接 : https://leetcode.cn/problems/distinct-subsequences/
public class Code01_DistinctSubsequences {
    public static int numDistinct1(String str, String target) {
        char[] s = str.toCharArray();
        char[] t = target.toCharArray();
        int n = s.length;
        int m = t.length;
        // dp[i][j]
        // s[前缀长度为i]的所有子序列中，有多少个子序列等于t[前缀长度为j]
        int[][] dp = new int[n + 1][m + 1];
        // s中前缀长度为i，有多少t为空的子序列
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 不要i位置的
                dp[i][j] = dp[i - 1][j];
                // 要i位置的
                if (s[i - 1] == t[j - 1]) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m];
    }
}
