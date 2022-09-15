package custom.code_2022_09;

/**
 * @ClassName LeetCode_713
 * @Author Duys
 * @Description
 * @Date 2022/9/13 15:44
 **/
// 713. 乘积小于 K 的子数组
public class LeetCode_713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        if (k < 1) {
            return 0;
        }
        for (int l = 0, r = 0, cur = 0; r < n; r++) {
            // cur记录当前 l...r窗口内，的乘积
            cur *= nums[r];
            // 缩窗口
            while (cur >= k) {
                cur /= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
