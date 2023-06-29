package week.week_2023_06_04;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code_01_LeetCode_2741
 * @date 2023年06月29日
 */
// 2741. 特别的排列
// 测试链接 : https://leetcode.cn/problems/special-permutations/
public class Code_01_LeetCode_2741 {
    // 根据题意：数组的长度在 2到14之内，那么我们就可以通过这个来想到一个状态压缩的动态规划
    // 并且记录上一次选择的是哪一个位置的数
    public int specialPerm(int[] nums) {
        int n = nums.length;
        // 当前状态对应的最后一个选择的位置，有多少合法的排列
        int[][] dp = new int[1 << n][n];
        for (int i = 0; i < (1 << n); i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(nums, n, 0, -1, dp);
    }

    public int process(int[] arr, int n, int status, int last, int[][] dp) {
        if (last >= 0 && dp[status][last] != -1) {
            return dp[status][last];
        }
        int mod = 1_000_000_007;
        int ans = 0;
        // 找到了一种合法的
        if (status == (1 << n) - 1) {
            ans = 1;
        } else {
            //开始选择能选择数
            for (int i = 0; i < n; i++) {
                if (status == 0 // status =0 说明刚刚开始的时候，什么数也没选择
                        || ((status & (1 << i)) == 0 && (arr[last] % arr[i] == 0 || arr[i] % arr[last] == 0))) {
                    ans = (ans + process(arr, n, status | (1 << i), i, dp)) % mod;
                }
            }
        }
        if (last >= 0) {
            dp[status][last] = ans;
        }
        return ans;
    }
}
