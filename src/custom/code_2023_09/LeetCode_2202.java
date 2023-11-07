package custom.code_2023_09;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2202
 * @date 2023年09月12日
 */
// 2202. K 次操作后最大化顶端元素
// https://leetcode.cn/problems/maximize-the-topmost-element-after-k-moves/
public class LeetCode_2202 {
    public int maximumTop(int[] nums, int k) {
        int ans = -1;
        int n = nums.length;
        if (n == 1) {
            return k % 2 == 0 ? nums[0] : -1;
        }
        for (int i = 0; i < Math.min(k + 1, n); i++) {
            if (i != k - 1) {
                ans = Math.max(ans, nums[i]);
            }
        }
        return ans;
    }
}
