package custom.code_2022_11;

/**
 * @ClassName LeetCode_1031
 * @Author Duys
 * @Description
 * @Date 2022/11/7 9:20
 **/
// 1031. 两个非重叠子数组的最大和
public class LeetCode_1031 {
    public int maxSumTwoNoOverlap(int[] nums, int L, int M) {
        int n = nums.length;
        // dp[i][0] 表示 0到i 连续L长度最大的子数组元素和
        // dp[i][1] 表示 0到i 连续M长度的最大子数组元素和
        // dp[i][2] 表示 i到size - 1 连续L长度的最大子数组元素和
        // dp[i][3] 表示 i到size - 1 连续M长度的最大子数组元素和
        int[][] dp = new int[n][4];
        int preSum = 0;
        int maxSum = 0;
        // 生成窗口
        for (int i = 0; i < L; i++) {
            preSum += nums[i];
        }
        maxSum = preSum;
        dp[L - 1][0] = maxSum;
        // 保证窗口大小只有L长度
        for (int i = L; i < n; i++) {
            preSum -= nums[i - L];
            preSum += nums[i];
            maxSum = Math.max(preSum, maxSum);
            dp[i][0] = maxSum;
        }
        preSum = 0;
        for (int i = 0; i < M; i++) {
            preSum += nums[i];
        }
        maxSum = preSum;
        dp[M - 1][1] = maxSum;
        for (int i = M; i < n; i++) {
            preSum -= nums[i - M];
            preSum += nums[i];
            maxSum = Math.max(preSum, maxSum);
            dp[i][1] = maxSum;
        }
        // 从右边往左计算一次
        preSum = 0;
        for (int i = n - 1; i >= n - L; i--) {
            preSum += nums[i];
        }
        maxSum = preSum;
        dp[n - L][2] = maxSum;
        for (int i = n - L - 1; i >= 0; i--) {
            preSum -= nums[i + L];
            preSum += nums[i];
            maxSum = Math.max(preSum, maxSum);
            dp[i][2] = maxSum;
        }

        preSum = 0;
        for (int i = n - 1; i >= n - M; i--) {
            preSum += nums[i];
        }
        maxSum = preSum;
        dp[n - M][3] = maxSum;
        for (int i = n - M - 1; i >= 0; i--) {
            preSum -= nums[i + M];
            preSum += nums[i];
            maxSum = Math.max(preSum, maxSum);
            dp[i][3] = maxSum;
        }
        // 计算L在M左边答案
        int ans = 0;
        for (int i = L; i <= n - M; i++) {
            ans = Math.max(ans, dp[i - 1][0] + dp[i][3]);
        }
        // 计算L在M的右边
        for (int i = M; i <= n - L; i++) {
            ans = Math.max(ans, dp[i - 1][1] + dp[i][2]);
        }
        return ans;
    }
}
