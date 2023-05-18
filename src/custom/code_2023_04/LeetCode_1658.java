package custom.code_2023_04;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1658
 * @Author Duys
 * @Description
 * @Date 2023/4/25 10:14
 **/
// 1658. 将 x 减到 0 的最小操作数
public class LeetCode_1658 {
    // 前缀和 + 滑动窗口
    // 问题转置，求得中间连续部分的累加和 == sum -x，找出最长的一段
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        int target = sum - x;
        if (target < 0) {
            return -1;
        }
        int subsum = 0;
        int left = 0;
        int ans = -1;
        for (int r = 0; r < n; r++) {
            subsum += nums[r];
            while (subsum > target) {
                subsum -= nums[left];
                left++;
            }
            if (subsum == target) {
                ans = Math.max(ans, r - left + 1);
            }
        }
        return ans < 0 ? -1 : (n - ans);
    }
}
