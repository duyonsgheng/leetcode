package custom.code_2022_11;

import java.util.Arrays;

/**
 * @ClassName LeetCode_891
 * @Author Duys
 * @Description
 * @Date 2022/11/18 11:06
 **/
// 891. 子序列宽度之和
public class LeetCode_891 {
    // 思路:
    // 1.每个nums[i]作为最大和最小贡献度是多少
    // 2.排序，当nums[i]作为最大值的时候有几个子序列
    // 3.排序后 当前作为最大是2^i 个子数组 作为最小是2^(n-1-i)个
    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int mod = (int) 1e9 + 7;
        long ans = 0;
        long[] pow = new long[n];
        pow[0] = 1;
        for (int i = 1; i < n; i++) {
            pow[i] = (pow[i - 1] << 1) % mod;
        }
        for (int i = 0; i < n; i++) {
            ans = (ans + (pow[i] - pow[n - i - 1]) * nums[i] % mod) % mod;
        }
        return (int) ans;
    }
}
