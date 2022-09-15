package custom.code_2022_07;

/**
 * @ClassName LeetCode_209
 * @Author Duys
 * @Description
 * @Date 2022/7/6 15:33
 **/
// 209. 长度最小的子数组
// 给定一个含有n个正整数的数组和一个正整数 target 。
//找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
//链接：https://leetcode.cn/problems/minimum-size-subarray-sum
public class LeetCode_209 {

    public static int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int n = nums.length;
        long[] sums = new long[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            sums[i] = sum;
        }
        if (sum < target) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        // 窗口额
        int l = 0;
        int r = 0;
        while (r < n && l <= r) {
            long diff = sums[r] - (l > 0 ? sums[l - 1] : 0);
            if (diff >= target) {
                while (sums[r] - (l > 0 ? sums[l - 1] : 0) >= target) {
                    min = Math.min(min, r - l + 1);
                    l++;
                }
            }
            r++;
        }
        return min;
    }

    public static void main(String[] args) {
        // 7
        //[2,3,1,2,4,3]
        int[] arr = {2, 3, 1, 2, 4, 3};
        int t = 7;
        minSubArrayLen(t, arr);
    }
}
