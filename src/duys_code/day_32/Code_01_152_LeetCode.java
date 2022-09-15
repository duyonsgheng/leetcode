package duys_code.day_32;

/**
 * @ClassName Code_01_152_LeetCode
 * @Author Duys
 * @Description 力扣： https://leetcode-cn.com/problems/maximum-product-subarray/
 * @Date 2021/12/3 15:25
 **/
public class Code_01_152_LeetCode {
    // 每次记录两个值，一个最大，一个最小。然后每次更新
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int ans = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMin = Math.min(nums[i], Math.min(nums[i] * min, nums[i] * max));
            int curMax = Math.max(nums[i], Math.max(nums[i] * min, nums[i] * max));
            max = curMax;
            min = curMin;
            ans = Math.max(ans, max);
        }
        return ans;
    }
}
