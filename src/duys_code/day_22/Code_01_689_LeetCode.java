package duys_code.day_22;

/**
 * @ClassName Code_01_689_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
 * @Date 2021/11/10 13:18
 **/
public class Code_01_689_LeetCode {

    /**
     * 给定数组nums由正整数组成，找到三个互不重叠的子数组的最大和。
     * 每个子数组的长度为k，我们要使这3*k个项的和最大化。
     * 返回每个区间起始索引的列表（索引从 0 开始）。如果有多个结果，返回字典序最小的一个。
     */
    // 1.先从0 ~ N-1 上求一个子数组最大的累加和
    // 2.再从N-1 ~ 0 反着求子数组最大的累加和
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if (nums == null || nums.length < 3 || k < 1) {
            return new int[]{};
        }
        int N = nums.length;
        int[] range = new int[N];
        int[] left = new int[N];
        int sum = 0;
        // 左边先默认是0到k长度的
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        range[0] = sum;
        left[k - 1] = 0;
        int max = sum;
        for (int i = k; i < N; i++) {
            sum = sum - nums[i - k] + nums[i];
            range[i - k + 1] = sum;
            left[i] = left[i - 1];
            if (sum > max) {
                max = sum;
                left[i] = i - k + 1;
            }
        }
        sum = 0;
        // 右边默认是N-k长度的
        for (int i = N - 1; i >= N - k; i--) {
            sum += nums[i];
        }
        max = sum;
        int[] right = new int[N];
        right[N - k] = N - k;
        for (int i = N - k - 1; i >= 0; i--) {
            sum = sum - nums[i + k] + nums[i];
            right[i] = right[i + 1];
            if (sum >= max) {
                max = sum;
                right[i] = i;
            }
        }
        int a = 0;
        int b = 0;
        int c = 0;
        max = 0;
        for (int i = k; i < N - 2 * k + 1; i++) { // 中间一块的起始点 (0...k-1)选不了 i == N-1
            int part1 = range[left[i - 1]];
            int part2 = range[i];
            int part3 = range[right[i + k]];
            if (part1 + part2 + part3 > max) {
                max = part1 + part2 + part3;
                a = left[i - 1];
                b = i;
                c = right[i + k];
            }
        }
        return new int[]{a, b, c};
    }

    // 这就是从0~i求子数组最大的累加和
    public static int[] maxSumArray1(int[] arr) {
        int N = arr.length;
        int[] help = new int[N];
        // help[i] 子数组必须以i位置结尾的情况下，累加和最大是多少？
        help[0] = arr[0];
        for (int i = 1; i < N; i++) {
            int p1 = arr[i];
            int p2 = arr[i] + help[i - 1];
            help[i] = Math.max(p1, p2);
        }
        // dp[i] 在0~i范围上，随意选一个子数组，累加和最大是多少？
        int[] dp = new int[N];
        dp[0] = help[0];
        for (int i = 1; i < N; i++) {
            int p1 = help[i];
            int p2 = dp[i - 1];
            dp[i] = Math.max(p1, p2);
        }
        return dp;
    }
}
