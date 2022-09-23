package custom.code_2022_09;

/**
 * @ClassName LeetCode_813
 * @Author Duys
 * @Description
 * @Date 2022/9/23 10:14
 **/
// 813. 最大平均值和的分组
public class LeetCode_813 {
    // 动态规划
    // dp[i] 当前来到i位置，必要 k个子数组
    // 9 1 2 3 9
    public double largestSumOfAverages3(int[] nums, int k) {
        int n = nums.length;
        double[] pre = new double[n + 1]; // 前缀和
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        // 0 1  2  3  4  5
        // 0 9 10 12 15 24
        // 0
        double[] dp = new double[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i] = (pre[n] - pre[i]) / (n - i);
        }
        for (int i = 0; i < k - 1; i++) {
            for (int j = 0; j < n; j++) {
                for (int m = j + 1; m < n; m++) {
                    dp[j] = Math.max(dp[j], (pre[m] - pre[j]) / (m - j) + dp[m]);
                }
            }
        }
        return dp[0];
    }

    public double largestSumOfAverages1(int[] nums, int k) {
        return process(nums, 0, k);
    }

    public double process(int[] arr, int index, int rest) {
        if (rest == 0) {
            return 0;
        }
        if (rest == 1) {
            int sum = 0;
            for (int i = index; i < arr.length; i++)
                sum += arr[i];
            return sum;
        }
        double sum = 0;
        double ans = 0;
        for (int i = index; i < arr.length; i++) {
            sum += arr[i];
            // 枚举这一刀划在那儿了
            double cur = sum / (i - index + 1);
            double next = process(arr, index + 1, rest - 1);
            ans = Math.max(next + cur, ans);
        }
        return ans;
    }

    public double largestSumOfAverages2(int[] nums, int k) {
        int n = nums.length;
        double[][] dp = new double[n + 1][k + 1];
        double[] preSums = new double[n + 1];
        for (int i = 0; i < n; i++) {
            preSums[i + 1] = preSums[i] + nums[i];
            dp[i + 1][1] = preSums[i + 1] / (i + 1);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= Math.min(i, k); j++) {
                // 当前这一刀划了，那么之前的就应该是之前位置上找最大的
                for (int m = 0; m < i; m++) {
                    dp[i][j] = Math.max(dp[i][j], dp[m][j - 1] + (preSums[i] - preSums[m]) / (i - m));
                }
            }
        }
        return dp[n][k];
    }

}
