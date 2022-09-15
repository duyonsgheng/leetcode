package week.week_2022_02_02;

import java.util.Arrays;

/**
 * @ClassName Code_05_LeetCode_2122
 * @Author Duys
 * @Description
 * @Date 2022/3/30 13:36
 **/
public class Code_05_LeetCode_2122 {
    //Alice 有一个下标从 0 开始的数组 arr ，由 n 个正整数组成。她会选择一个任意的 正整数 k 并按下述方式创建两个下标从 0 开始的新整数数组 lower 和 higher ：
    //
    //对每个满足 0 <= i < n 的下标 i ，lower[i] = arr[i] - k
    //对每个满足 0 <= i < n 的下标 i ，higher[i] = arr[i] + k
    //不幸地是，Alice 丢失了全部三个数组。但是，她记住了在数组 lower 和 higher 中出现的整数，但不知道每个整数属于哪个数组。请你帮助 Alice 还原原数组。
    //给你一个由 2n 个整数组成的整数数组 nums ，其中 恰好 n 个整数出现在 lower ，剩下的出现在 higher ，还原并返回 原数组 arr 。如果出现答案不唯一的情况，返回 任一 有效数组。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/recover-the-original-array
    public static int[] recoverArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        if (nums.length == 1) {
            return new int[]{nums[0]};
        }
        Arrays.sort(nums);
        int n = nums.length;
        int m = n >> 1;
        // 尝试谁是大数组的第0个
        // 小数组的第0个一定是 0位置的数
        for (int first = 1; first <= m; first++) {
            // higher[i] = arr[i] + k
            // lower[i]  = arr[i] - k
            // 那么 higher[0] - lower[0] = 2k
            int d = nums[first] - nums[0];
            // 那么d一定是一个偶数
            if (d > 0 && (d & 1) == 0) {
                // 试图生成原数组
                int[] ans = new int[m];
                int index = 0;
                int k = d >> 1;
                boolean[] set = new boolean[n];
                int l = 0;
                int r = first;
                while (r < n) {
                    while (set[l]) {
                        l++;
                    }
                    if (l == r) {
                        r++;
                    } else if (nums[r] - nums[l] > d) {
                        break;
                    } else if (nums[r] - nums[l] < d) {
                        r++;
                    } else {
                        set[r++] = true;
                        ans[index++] = nums[l++] + k;
                    }
                }
                if (index == m) {
                    return ans;
                }
            }
        }
        return null;
    }
}
