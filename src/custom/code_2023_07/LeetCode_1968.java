package custom.code_2023_07;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1968
 * @date 2023年07月17日
 */
// 1968. 构造元素不等于两相邻元素平均值的数组
// https://leetcode.cn/problems/array-with-elements-not-equal-to-average-of-neighbors/
public class LeetCode_1968 {
    // (nums[i-1] + nums[i+1]) / 2 不等于 nums[i] 均成立
    public int[] rearrangeArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int m = (n + 1) / 2;
        int[] ans = new int[n];
        // 分成两部分，分别填充，
        // 如果左右都很小，当前位置很大，那么就满足题意了
        // 如果左右都很大，当前位置很小，那么也满足题意了
        // nums = [6,2,0,9,7]
        // 0 2 6 7 9
        // 0 7 2 9 6
        for (int i = 0, j = 0; i < m; i++) {
            ans[j++] = nums[i];
            if (i + m < n) {
                ans[j++] = nums[i + m];
            }
        }
        return ans;
    }
}
