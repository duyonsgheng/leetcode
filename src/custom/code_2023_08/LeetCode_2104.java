package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2104
 * @date 2023年08月17日
 */
// 2104. 子数组范围和
// https://leetcode.cn/problems/sum-of-subarray-ranges/
public class LeetCode_2104 {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int j = i; j < n; j++) {
                min = Math.min(nums[j], min);
                max = Math.max(nums[j], max);
                ans += max - min;
            }
        }
        return ans;
    }
}
