package hope.dp_3;

/**
 * @author Mr.Du
 * @ClassName Code03_InterleavingString
 * @date 2023年12月21日 15:30
 */
// 交错字符串
// 给定三个字符串 s1、s2、s3
// 请帮忙验证s3是否由s1和s2交错组成
// 测试链接 : https://leetcode.cn/problems/interleaving-string/
public class Code03_InterleavingString {
    public static boolean isInterleave1(String str1, String str2, String str3) {
        if (str1.length() + str2.length() != str3.length()) {
            return false;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        char[] s3 = str3.toCharArray();
        int n = s1.length;
        int m = s2.length;
        // dp[i][j] s1的前缀长度为i s2前缀长度为j，能否组成s3的前缀长度i+j
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (s1[i - 1] != s3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        for (int i = 1; i <= m; i++) {
            if (s2[i - 1] != s3[i - 1]) {
                break;
            }
            dp[0][i] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = (s1[i - 1] == s3[i + j - 1] && dp[i - 1][j]) || (s2[j - 1] == s3[i + j - 1] && dp[i][j - 1]);
            }
        }
        return dp[n][m];
    }
}
