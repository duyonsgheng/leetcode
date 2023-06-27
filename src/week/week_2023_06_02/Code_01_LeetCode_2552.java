package week.week_2023_06_02;

/**
 * @ClassName Code_01_LeetCode_2552
 * @Author Duys
 * @Description
 * @Date 2023/6/15 9:03
 **/
// 2552. 统计上升四元组
// https://leetcode.cn/problems/count-increasing-quadruplets/
public class Code_01_LeetCode_2552 {
    // 思路
    // 当前来到i位置，我希望我能知道之前的信息 满足 小 大 中 这个三元组的信息
    // 我当前位置只要能大于中间最大的元素，那么我就可以用之前的信息求得答案，然后依次更新好，去下一个位置继续
    public long countQuadruplets1(int[] nums) {
        int n = nums.length;
        long ans = 0;
        // dp[j]含义 :
        //    ............................l
        // 目前假设刚来到l位置，那么在l之前的范围上
        // 位置 : 0....i....j....k....l-1
        // 如果j做中间点，请问有多少三元组满足 : arr[i] < arr[k] < arr[j]
        // 就是 : 小 大(j位置的数) 中
        // 这种三元组的数量，就是dp[j]的含义
        long[] dp = new long[n];
        for (int l = 1; l < n; l++) {
            // 根据之前的信息得到当前位置的答案
            for (int j = 0; j < l; j++) {
                if (nums[j] < nums[l]) {
                    ans += dp[j];
                }
            }
            // 然后统计之前比自己大的位置
            int cnt = 0;
            for (int j = 0; j < l; j++) {
                if (nums[j] < nums[l]) {
                    cnt++;
                } else {
                    dp[j] += cnt;
                }
            }
        }
        return ans;
    }

    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        long ans = 0;
        long[] dp = new long[n];
        for (int l = 1; l < n; l++) {
            // 然后统计之前比自己大的位置
            int cnt = 0;
            for (int j = 0; j < l; j++) {
                if (nums[j] < nums[l]) {
                    cnt++;
                    ans += dp[j];
                } else {
                    dp[j] += cnt;
                }
            }
        }
        return ans;
    }
}
