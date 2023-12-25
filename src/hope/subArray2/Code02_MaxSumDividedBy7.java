package hope.subArray2;

/**
 * @author Mr.Du
 * @ClassName Code02_MaxSumDividedBy7
 * @date 2023年12月25日 19:17
 */
// 子序列累加和必须被7整除的最大累加和
// 给定一个非负数组nums，
// 可以任意选择数字组成子序列，但是子序列的累加和必须被7整除
// 返回最大累加和
public class Code02_MaxSumDividedBy7 {
    public static int maxSum1(int[] nums) {
        return f(nums, 0, 0);
    }

    public static int f(int[] nums, int i, int s) {
        if (i == nums.length) {
            return s % 7 == 0 ? s : 0;
        }
        return Math.max(f(nums, i + 1, s), f(nums, i + 1, s + nums[i]));
    }

    public static int maxSum2(int[] nums) {
        int n = nums.length;
        // dp[i][j]:num[0....i-1]
        // nums的前i个数形成的子序列一定要做大，子序列累加和 %7 ==j
        // 这样的子序列累加和最大是多少
        // 如果不存在这样的子序列，那么dp[i][j] =-1
        int[][] dp = new int[n + 1][7];
        dp[0][0] = 0;
        for (int j = 1; j < 7; j++) {
            dp[0][j] = -1;
        }
        for (int i = 1, x, cur, need; i <= n; i++) {
            x = nums[i - 1];
            cur = nums[i - 1] % 7;
            for (int j = 0; j < 7; j++) {
                dp[i][j] = dp[i - 1][j];
                // need
                //need = cur <= j ? (j - cur) : (j - cur + 7);
                need = (7 + j - cur) % 7;
                if (dp[i - 1][need] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][need] + x);
                }
            }
        }
        return dp[n][0];
    }
}
