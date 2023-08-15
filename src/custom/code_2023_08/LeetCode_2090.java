package custom.code_2023_08;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2090
 * @date 2023年08月14日
 */
// 2090. 半径为 k 的子数组平均值
// https://leetcode.cn/problems/k-radius-subarray-averages/
public class LeetCode_2090 {
    public static int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        // 7,4,3,9,1,8,5,2,6
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            if (i - k >= 0 && i + k < n) {
                ans[i] = (int) (sum[i + k + 1] - sum[i - k]) / (k * 2 + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        getAverages(new int[]{7, 4, 3, 9, 1, 8, 5, 2, 6}, 3);
    }
}
