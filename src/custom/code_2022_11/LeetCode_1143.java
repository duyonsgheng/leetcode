package custom.code_2022_11;

/**
 * @ClassName LeetCode_1143
 * @Author Duys
 * @Description
 * @Date 2022/11/16 16:36
 **/
// 1143. 最长公共子序列
public class LeetCode_1143 {
    // 动态规划
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] arr1 = text1.toCharArray();
        char[] arr2 = text1.toCharArray();
        int n1 = arr1.length;
        int n2 = arr2.length;
        // dp[i][j] text1中0到i text20到j
        int[][] dp = new int[n1 + 1][n2 + 2];
        // 0 0 位置如果两个位置的字符相同，就是1，否是0
        dp[0][0] = arr1[0] == arr2[0] ? 1 : 0;
        // 第一行表示arr1 中和arr2 的0位置匹配
        for (int i = 1; i < n1; i++) {
            dp[i][0] = arr1[i] == arr2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < n2; i++) {
            dp[0][i] = arr1[0] == arr2[i] ? 1 : dp[0][i - 1];
        }
        // 普遍位置
        for (int i = 1; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (arr1[i] == arr2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[n1 - 1][n2 - 1];
    }
}
