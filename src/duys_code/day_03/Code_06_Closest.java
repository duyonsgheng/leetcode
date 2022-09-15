package duys_code.day_03;

import java.util.Arrays;

/**
 * @ClassName Code_06_Closest
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/closest-subsequence-sum/
 * @Date 2021/9/22 11:19
 **/
public class Code_06_Closest {
    /**
     * 题意：
     * 给你一个整数数组 nums 和一个目标值 goal 。
     * 你需要从 nums 中选出一个子序列，使子序列元素总和最接近 goal 。也就是说，如果子序列元素和为 sum ，你需要 最小化绝对差 abs(sum - goal) 。
     * 返回 abs(sum - goal) 可能的 最小值 。
     * 注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。
     * 注意：
     * 1 <= nums.length <= 40
     * -10^7 <= nums[i] <= 10^7
     * -10^9 <= goal <= 10^9
     * 给了范围。已经是10^7和10^9这么大的数了，不能用动态规划来做了
     */
    /**
     * 根据数据量猜解法：因为数组的长度很少，那么使用啥？
     * 二分(分治)。数组的左边和右边。，各20个数，来求一个前缀和。答案可能只再左边，也可能只在右边，也可能左边和右边都有
     */
    //
    public static int minAbsDifference(int[] nums, int goal) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] left = new int[1 << 20];
        int[] right = new int[1 << 20];
        int le = process(nums, 0, nums.length >> 1, 0, 0, left);
        int ri = process(nums, nums.length >> 1, nums.length, 0, 0, right);
        Arrays.sort(left, 0, le);
        Arrays.sort(right, 0, ri--);
        int ans = Math.abs(goal);
        // 从左边数组拿出每一个数，和右边的数组累加和里的数进行合并计算，这样子当左边选0的时候，用的全部是右边的
        // 当右边选择0的时候，用的全部是左边的
        // 当左右都不为0的时候，就是各自那一部分数进行计算
        for (int i = 0; i < le; i++) {
            int rest = goal - left[i];
            while (ri > 0 && Math.abs(rest - right[ri - 1]) <= Math.abs(rest - right[ri])) {
                ri--;
            }
            ans = Math.min(ans, Math.abs(rest - right[ri]));
        }
        return ans;
    }

    public static int process(int[] nums, int index, int end, int sum, int fill, int[] arr) {
        if (index == end) {
            arr[fill++] = sum;
        } else {
            // 不要当前数，求一个累加和
            fill = process(nums, index + 1, end, sum, fill, arr);
            // 要当前数，然后求一个累加和
            fill = process(nums, index + 1, end, sum + nums[index], fill, arr);
        }
        return fill;
    }
}
