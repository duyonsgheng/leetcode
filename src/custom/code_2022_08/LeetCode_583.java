package custom.code_2022_08;

/**
 * @ClassName LeetCode_583
 * @Author Duys
 * @Description
 * @Date 2022/8/29 13:01
 **/
// 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
// 每步 可以删除任意一个字符串中的一个字符。
public class LeetCode_583 {

    public static int minDistance(String word1, String word2) {
        if ((word1 == null || word1.length() <= 0) && (word2 == null || word2.length() <= 0)) {
            return 0;
        }
        if (word1 == null || word1.length() <= 0) {
            return word2.length();
        }
        if (word2 == null || word2.length() <= 0) {
            return word1.length();
        }
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= n; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                if (c1 == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 不相等，至少能删除一个
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        String word1 = "leetcode", word2 = "etco";
        System.out.println(minDistance(word1, word2));
    }
}
