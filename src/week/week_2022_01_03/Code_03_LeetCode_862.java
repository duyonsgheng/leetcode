package week.week_2022_01_03;

/**
 * @ClassName Code_03_LeetCode_862
 * @Author Duys
 * @Description
 * @Date 2022/3/31 13:00
 **/
public class Code_03_LeetCode_862 {
    // 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
    //子数组 是数组中 连续 的一部分。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k

    public int shortestSubarray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length + 1;
        long[] sum = new long[n];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int[] stack = new int[n];
        int size = 1;
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int most = mostRight(sum, stack, size, sum[i] - k);
            ans = Math.min(ans, most == -1 ? Integer.MAX_VALUE : (i - most));
            while (size > 0 && sum[stack[size - 1]] >= sum[i]) {
                size--;
            }
            stack[size++] = i;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int mostRight(long[] sum, int[] stack, int size, long aim) {
        int l = 0;
        int r = size - 1;
        int ans = -1;
        int m = 0;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (sum[stack[m]] <= aim) {
                ans = stack[m];
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public int shortestSubarray2(int[] nums, int k) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[] dq = new int[n + 1];
        int ans = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;
        for (int i = 0; i < n + 1; i++) {
            while (l != r && sum[i] - sum[dq[l]] >= k) {
                ans = Math.min(ans, i - dq[l++]);
            }
            while (l != r && sum[dq[r - 1]] >= sum[i]) {
                r--;
            }
            dq[r++] = i;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
