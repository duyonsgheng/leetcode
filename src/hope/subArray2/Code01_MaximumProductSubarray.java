package hope.subArray2;

/**
 * @author Mr.Du
 * @ClassName Code01_MaximumProductSubarray
 * @date 2023年12月25日 19:13
 */
// 乘积最大子数组
// 给你一个整数数组 nums
// 请你找出数组中乘积最大的非空连续子数组
// 并返回该子数组所对应的乘积
// 测试链接 : https://leetcode.cn/problems/maximum-product-subarray/
public class Code01_MaximumProductSubarray {
    public static int maxProduct(int[] nums) {
        int ans = nums[0];
        for (int i = 1, min = nums[0], max = nums[0], curmin, curmax; i < nums.length; i++) {
            curmin = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
            curmax = Math.max(nums[i], Math.max(min * nums[i], max * nums[i]));
            min = curmin;
            max = curmax;
            ans = Math.max(ans, max);
        }
        return ans;
    }
}
