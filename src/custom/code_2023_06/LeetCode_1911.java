package custom.code_2023_06;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1911
 * @date 2023年06月28日
 */
// 1911. 最大子序列交替和
public class LeetCode_1911 {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] odd = new long[n]; // i作为奇数
        long[] even = new long[n]; // i作为偶数
        even[0] = nums[0];
        for (int i = 1; i < n; i++) {
            // i作为偶数
            even[i] = Math.max(nums[i] + odd[i - 1], even[i - 1]);
            // i作为奇数，需要被减去
            odd[i] = Math.max(even[i - 1] - nums[i], odd[i - 1]);
        }
        // 这里只需要偶数就行了，因为奇数会被减掉，当然也可以
        return Math.max(even[n - 1], odd[n - 1]);
    }
}
