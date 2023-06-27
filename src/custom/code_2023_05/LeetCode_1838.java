package custom.code_2023_05;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1838
 * @Author Duys
 * @Description
 * @Date 2023/5/29 9:36
 **/
// 1838. 最高频元素的频数
public class LeetCode_1838 {
    // 排序+滑动窗口
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int l = 0, ans = 1;
        for (int r = 1; r < n; r++) {
            // 如果把当前窗口内全部变为nums[r] ，需要多少步
            // 当前位置把前面位置的累计上
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                // 需要减去前面位置累计的
                total -= (nums[r] - nums[l]);
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}

