package week.week_2023_05_04;

/**
 * @ClassName Code_02_SplitNtoKMaxProduct
 * @Author Duys
 * @Description
 * @Date 2023/5/25 9:29
 **/
// 来自华为
// 一个数字n，一定要分成k份
// 得到的乘积尽量大是多少
// 数字n和k，可能非常大，到达10^12规模
// 结果可能更大，所以返回结果对1000000007取模
public class Code_02_SplitNtoKMaxProduct {
    // 根据数学特性可知
    // 一个数被分为多个数的最大乘积，哪些这些数从平均值开始
    // 本题意就是从平均值开始 n/k ，然后 n%k 剩下了几个可以把前面的几个数拉高
    // 如果 n/k 较大，n%k 也较大的时候，使用循环的话，可能会超时
    // 快速幂

    // 贪心的解
    // 这不是最优解，只是展示贪心思路
    public static int maxValue1(int n, int k) {
        if (k == 0 || n < k) {
            return -1;
        }
        // 数字n，一定要分k份
        // 每份先得多少，n/k
        int a = n / k;
        // 有多少份可以升级成a+1
        int b = n % k;
        int ans = 1;
        for (int i = 0; i < b; i++) {
            ans *= a + 1;
        }
        for (int i = 0; i < k - b; i++) {
            ans *= a;
        }
        return ans;
    }

    public static int maxValue2(int n, int k) {
        if (k == 0 || n < k) {
            return -1;
        }
        int mod = 1_000_000_007;
        long a = n / k;
        long b = n % k;
        long ans1 = power(a + 1, b, mod);
        long ans2 = power(a, k - b, mod);
        return (int) (ans1 * ans2) % mod;
    }

    // 快速幂的解 a ^ b 然后 % mod
    public static long power(long a, long b, int mod) {
        long ans = 1;
        long tmp = a;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans = (ans * tmp) % mod;
            }
            b >>= 1;
            tmp = (tmp * tmp) % mod;
        }
        return ans;
    }
}
