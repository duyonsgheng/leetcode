package week.week_2022_11_05;

import java.util.Arrays;

/**
 * @ClassName Code_03_LeetCode_891
 * @Author Duys
 * @Description
 * @Date 2022/12/1 10:36
 **/
// https://leetcode.cn/problems/sum-of-subsequence-widths/
public class Code_03_LeetCode_891 {

    public static int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        // 列出等式观察，每个位置的子序列有多少个 2^i次方个
        // 来到一个位置 nums[i] * 2^i - nums[i-1]*2^(i-1)- nums[i-2]*2^(i-2)
        // 下一个位置，左边相加，右边乘以2然后加上当前-前一个位置
        int mod = 1_000_000_007;
        long ans = 0;
        long a = 0, b = 0, c = 1, d = 1;
        for (int i = 1; i < nums.length; i++) {
            a = (d * nums[i]) % mod;
            b = (b * 2 + nums[i - 1]) % mod;
            ans = (ans + a - b + mod) % mod;
            c = (c * 2) % mod;
            d = (d + c) % mod;
        }
        return (int) ans;
    }
}
