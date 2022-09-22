package custom.code_2022_09;

/**
 * @ClassName LeetCode_795
 * @Author Duys
 * @Description
 * @Date 2022/9/21 13:09
 **/
// 795. 区间子数组个数
public class LeetCode_795 {

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        // 子数组必须以i结尾的情况有几个满足的
        // 1.arr[i] > r dp[i] =0
        // 2.arr[i] < l dp[i] = dp[i-1;]
        // 3.再 l r区间内，当前位置到之前不满足的位置，有几个数
        int pre = 0;
        int preIndex = -1;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > right) {
                preIndex = i;
                pre = 0;
            } else if (nums[i] < left) {
                ans += pre;
            } else {
                pre = i - preIndex;
                ans += pre;
            }
        }
        return ans;
    }
}
