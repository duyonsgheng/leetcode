package custom.code_2023_05;

/**
 * @ClassName LeetCode_1749
 * @Author Duys
 * @Description
 * @Date 2023/5/17 15:05
 **/
// 1749. 任意子数组和的绝对值的最大值
public class LeetCode_1749 {
    // 前缀和
    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int ans = 0;
        for (int i = 1, max = 0, min = 0; i <= n; i++) {
            ans = Math.max(ans, Math.abs(sums[i] - min));
            ans = Math.max(ans, Math.abs(sums[i] - max));
            // 更新最小
            min = Math.min(min, sums[i]);
            // 更新最大
            max = Math.max(max, sums[i]);
        }
        return ans;
    }
}
