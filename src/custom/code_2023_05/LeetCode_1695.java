package custom.code_2023_05;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_1695
 * @Author Duys
 * @Description
 * @Date 2023/5/5 14:38
 **/
// 1695. 删除子数组的最大得分
public class LeetCode_1695 {
    // 窗口
    public int maximumUniqueSubarray(int[] nums) {
        int max = 0;
        int sum = 0;
        Set<Integer> set = new HashSet<>();
        for (int l = 0, r = 0; r < nums.length; r++) {
            while (set.contains(nums[r])) {
                sum -= nums[l];
                set.remove(nums[l]);
                l++;
            }
            sum += nums[r];
            set.add(nums[r]);
            max = Math.max(sum, max);
        }
        return max;
    }
}
