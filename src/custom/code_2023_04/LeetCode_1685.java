package custom.code_2023_04;

/**
 * @ClassName LeetCode_1685
 * @Author Duys
 * @Description
 * @Date 2023/4/26 15:46
 **/
// 1685. 有序数组中差绝对值之和
public class LeetCode_1685 {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            // 左边的绝对值之和，因为数组是非递减的，所以绝对值 = i-0 + i-1 + i-2 ....i-(i-1)
            int left = nums[i] * i - sums[i];
            // 右边的绝对值之和，一样的道理 i位置被减去 (n-i-1)次
            int right = sums[n] - sums[i + 1] - (n - i - 1) * nums[i];
            ans[i] = left + right;
        }
        return ans;
    }
}
