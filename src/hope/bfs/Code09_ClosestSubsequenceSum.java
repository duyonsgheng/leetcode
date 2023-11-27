package hope.bfs;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code09_ClosestSubsequenceSum
 * @date 2023年11月24日 10:28
 */
// 最接近目标值的子序列和
// 给你一个整数数组 nums 和一个目标值 goal
// 你需要从 nums 中选出一个子序列，使子序列元素总和最接近 goal
// 也就是说，如果子序列元素和为 sum ，你需要 最小化绝对差 abs(sum - goal)
// 返回 abs(sum - goal) 可能的 最小值
// 注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。
// 数据量描述:
// 1 <= nums.length <= 40
// -10^7 <= nums[i] <= 10^7
// -10^9 <= goal <= 10^9
// 测试链接 : https://leetcode.cn/problems/closest-subsequence-sum/
public class Code09_ClosestSubsequenceSum {
    public static int maxn = 1 << 20;
    public static int[] lsum = new int[maxn];
    public static int[] rsum = new int[maxn];

    public static int fill;

    public static int minAbsDifference(int[] nums, int goal) {
        int n = nums.length;
        long min = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                max += nums[i];
            } else {
                min += nums[i];
            }
        }
        if (max < goal) {
            return (int) Math.abs(max - goal);
        }
        if (min > goal) {
            return (int) Math.abs(min - goal);
        }
        Arrays.sort(nums);
        fill = 0;
        process(nums, 0, n >> 1, 0, lsum);
        int lsize = fill;
        process(nums, n >> 1, n, 0, rsum);
        int rsize = fill;
        Arrays.sort(lsum, 0, lsize);
        Arrays.sort(rsum, 0, rsize);
        int ans = Math.abs(goal);
        for (int i = 0, j = rsize - 1; i < lsize; i++) {
            while (j > 0 && Math.abs(goal - lsum[i] - rsum[j - 1]) <= Math.abs(goal - lsum[i] - rsum[j])) {
                j--;
            }
            ans = Math.min(ans, Math.abs(goal - lsum[i] - rsum[j]));
        }
        return ans;
    }

    public static void process(int[] nums, int i, int e, int s, int[] sum) {
        if (i == e) {
            sum[fill++] = s;
        } else {
            int j = i + 1;
            // 重复的数，可以合并计算，这样就不用重复数字的位置依次展开
            while (j < e && nums[j] == nums[i])
                j++;
            for (int k = 0; k <= j; k++) {
                process(nums, j, e, s + k * nums[i], sum);
            }
        }
    }
}
