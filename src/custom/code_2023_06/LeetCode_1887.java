package custom.code_2023_06;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1887
 * @Author Duys
 * @Description
 * @Date 2023/6/15 10:43
 **/
// 1887. 使数组元素相等的减少操作次数
// 给你一个整数数组 nums ，你的目标是令 nums 中的所有元素相等。完成一次减少操作需要遵照下面的几个步骤：
//
//找出 nums 中的 最大 值。记这个值为 largest 并取其下标 i （下标从 0 开始计数）。如果有多个元素都是最大值，则取最小的 i 。
//找出 nums 中的 下一个最大 值，这个值 严格小于 largest ，记为 nextLargest 。
//将 nums[i] 减少到 nextLargest 。
//返回使 nums 中的所有元素相等的操作次数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/reduction-operations-to-make-the-array-elements-equal
public class LeetCode_1887 {
    // 把数组全部变为最小值
    public int reductionOperations(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int[] cnt = new int[max];    // 每个数计数
        int min = Integer.MAX_VALUE; // 最小值抓出来
        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i]]++;
            min = Math.min(nums[i], min);
        }
        int ans = 0, sum = 0;
        for (int i = max - 1; i >= 0; i--) {
            if (cnt[i] != 0 && i != min) { // 当前位置要变为最小值，
                sum += cnt[i]; // 有几个要改变的
                ans += sum;
            }
        }
        return ans;
    }
}
