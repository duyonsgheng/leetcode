package custom.code_2022_10;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_918
 * @Author Duys
 * @Description
 * @Date 2022/10/17 17:15
 **/
//918. 环形子数组的最大和
public class LeetCode_918 {
    // 1,-2,3,-2  1,-2,3,-2 拼接起来
    public static int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int max = nums[0];
        int sum = nums[0];
        int min = 0;
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sum += nums[i];
            // 子数组以当前数结尾的情况下
            dp[i] = nums[i] + Math.max(0, dp[i - 1]);
            max = Math.max(dp[i], max);
        }
        for (int i = 1; i < n - 1; i++) {
            // 最小
            dp[i] = nums[i] + Math.min(0, dp[i - 1]);
            min = Math.min(dp[i], min);
        }
        return Math.max(sum - min, max);
    }

    public int maxSubarraySumCircular2(int[] arr) {
        int maxSum = arr[0];
        int minSum = arr[0];
        int pre1 = 0;
        int pre2 = 0;
        int allSum = 0;
        for (int num : arr) {
            allSum += num;
            pre1 = Math.max(num, pre1 + num);
            maxSum = Math.max(maxSum, pre1);
            pre2 = Math.min(num, pre2 + num);
            minSum = Math.min(minSum, pre2);
        }
        if (maxSum < 0) {
            return maxSum;
        }
        return Math.max(allSum - minSum, maxSum);
    }

    public static void main(String[] args) {
        int[] arr = {1, -2, 3, -2};
        System.out.println(maxSubarraySumCircular(arr));
    }
}
