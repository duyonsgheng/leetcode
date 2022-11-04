package custom.code_2022_11;

/**
 * @ClassName LeetCode_1027
 * @Author Duys
 * @Description
 * @Date 2022/11/4 15:46
 **/
// 1027. 最长等差数列
public class LeetCode_1027 {

    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][1001];
        int ans = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int d = nums[i] - nums[j] + 500;
                dp[i][d] = dp[j][d] + 1;
                ans = Math.max(ans, dp[i][d]);
            }
        }
        // 算上当前位置
        return ans + 1;
    }
}
