package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2140
 * @date 2023年08月31日
 */
// https://leetcode.cn/problems/solving-questions-with-brainpower/
// 2140. 解决智力问题
public class LeetCode_2140 {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            int c = Math.min(i + questions[i][1] + 1, n);
            dp[c] = Math.max(dp[c], dp[i] + questions[i][0]);
        }
        return dp[n];
    }
}
