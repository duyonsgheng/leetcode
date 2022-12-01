package custom.code_2022_11;

/**
 * @ClassName LeetCode_1262
 * @Author Duys
 * @Description
 * @Date 2022/11/30 10:39
 **/
// 1262. 可被三整除的最大和
public class LeetCode_1262 {
    public static int maxSumDivThree(int[] nums) {
        return process(nums, 0, 0);
    }

    public static int process(int[] arr, int index, int preSum) {
        if (index == arr.length) {
            return preSum % 3 == 0 ? preSum : -1;
        }
        // 不要当前数
        int p1 = process(arr, index + 1, preSum);

        int p2 = process(arr, index + 1, preSum + arr[index]);

        if (p1 == -1) {
            return p2;
        }
        if (p2 == -1) {
            return p1;
        }
        return Math.max(p1, p2);
    }

    public static int maxSumDivThree1(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int[][] dp = new int[n + 1][3];
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            // 要和不要取大的
            if (nums[i - 1] % 3 == 0) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][0] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][1] + nums[i - 1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][2] + nums[i - 1]);
            } else if (nums[i - 1] % 3 == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + nums[i - 1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + nums[i - 1]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] + nums[i - 1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + nums[i - 1]);
            }
        }
        return dp[n][0];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4};
        System.out.println(maxSumDivThree(arr));
    }
}
