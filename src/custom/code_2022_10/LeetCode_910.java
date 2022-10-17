package custom.code_2022_10;

import java.util.Arrays;

/**
 * @ClassName LeetCode_910
 * @Author Duys
 * @Description
 * @Date 2022/10/17 11:37
 **/
// 910. 最小差值 II
public class LeetCode_910 {

    public int smallestRangeII(int[] nums, int k) {
        int n = nums.length;
        // 分析：
        // 可能性1：i -k，j +k，元素差值为 j-i +2k
        // 可能性2：i +k，j -k，元素差值为 j-i -2k
        // 为了试得差值最小，所以选择可能性2，前i个元素+k，后n-i个元素 -k、
        // 排序后
        // 前i个元素+k 后n-i个元素 -k
        // 数组前i个元素+k，更新后，最大值nums[i-1]+k，最小值nums[0]+k
        // 数组后n-i个元素-k，更新后，最大值nums[n-1] -k，最小值nums[i]-k
        Arrays.sort(nums);
        int ans = nums[n - 1] - nums[0];
        for (int i = 0; i < n - 1; i++) {
            int a = nums[i];
            int b = nums[i + 1];
            int big = Math.max(nums[n - 1] - k, a + k);
            int min = Math.min(nums[0] + k, b - k);
            ans = Math.min(ans, big - min);
        }
        return ans;
    }
}
