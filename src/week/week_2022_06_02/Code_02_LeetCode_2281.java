package week.week_2022_06_02;

/**
 * @ClassName Code_02_LeetCode_2281
 * @Author Duys
 * @Description
 * @Date 2022/6/16 10:23
 **/
// 2281. 巫师的总力量和
// 作为国王的统治者，你有一支巫师军队听你指挥。
//给你一个下标从 0开始的整数数组strength，其中strength[i]表示第i位巫师的力量值。对于连续的一组巫师（也就是这些巫师的力量值是strength的子数组），总力量定义为以下两个值的乘积：
//巫师中 最弱的能力值。
//组中所有巫师的个人力量值 之和。
//请你返回 所有巫师组的 总力量之和。由于答案可能很大，请将答案对109 + 7取余后返回。
//子数组是一个数组里 非空连续子序列。
//链接：https://leetcode.cn/problems/sum-of-total-strength-of-wizards
public class Code_02_LeetCode_2281 {
    public static final long mod = 1000000007;

    // 整体使用单调栈，但是局部的我们怎么算所有子数组的累加和？？？
    // 使用前缀和的前缀和
    public static int totalStrength(int[] strength) {
        int n = strength.length;
        long sum = strength[0];
        long[] sumOfSum = new long[n];
        sumOfSum[0] = strength[0];
        for (int i = 1; i < n; i++) {
            sum += strength[i];
            sumOfSum[i] = (sumOfSum[i - 1] + sum) % mod;
        }
        // 准备一个栈
        int[] stack = new int[n];
        int size = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            // 以当前 i 位置的数作为最小值的情况下，地下压了几个，就结算几个
            while (size > 0 && strength[stack[size - 1]] >= strength[i]) {
                int m = stack[--size];
                int l = size > 0 ? stack[size - 1] : -1;
                ans += magicSum(strength, sumOfSum, l, m, i);
                ans %= mod;
            }
            stack[size++] = i;
        }
        while (size > 0) {
            int m = stack[--size];
            int l = size > 0 ? stack[size - 1] : -1;
            ans += magicSum(strength, sumOfSum, l, m, n);
            ans %= mod;
        }
        return (int) ans;
    }

    // 计算某一个区域内所有的子数组的和

    /**
     * l       min          r
     * 2  3  4  5  6  7  8  9
     * 求：
     * 3~5 3~6 3~7 3~8
     * 4~5 4~6 4~7 4~8
     * 5~5 5~6 5~7 5~8
     * sumofsum:
     * 0 : 0~0
     * 1 : 0~0 0~1
     * 2 : 0~0 0~1 0~2
     * ..............
     * 8 :0~0 0~1 0~2 0~3 0~4 0~5 0~6 0~7 0~8
     * <p>
     * 8 - 4:  0~5 0~6 0~7 0~8
     * <p>
     * 0~5   - 0~2 = 3~5
     * 0~6   - 0~2 = 3~6
     * 0~7   - 0~2 = 3~7
     * ...........
     * 所以就有了
     * 左边是：(m-l) *(sumofsum[r-1] - sumofsum[m-1])
     * 右边是：(r-m) *(sumofsum[m-1] - sumofsum[l-1])
     */

    public static long magicSum(int[] arr, long[] sumSum, int l, int m, int r) {
        long left = (long) (m - l) * (sumSum[r - 1] - (m - 1 >= 0 ? sumSum[m - 1] : 0) + mod) % mod;
        long right = (long) (r - m) * ((m - 1 >= 0 ? sumSum[m - 1] : 0) - (l - 1 >= 0 ? sumSum[l - 1] : 0) + mod) % mod;
        return (long) arr[m] * ((left - right + mod) % mod);
    }
}
