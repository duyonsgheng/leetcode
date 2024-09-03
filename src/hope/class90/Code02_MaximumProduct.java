package hope.class90;

/**
 * @author Mr.Du
 * @ClassName Code02_MaximumProduct
 * @date 2024年08月26日 下午 05:01
 */
// 分成k份的最大乘积
// 一个数字n一定要分成k份，得到的乘积尽量大是多少
// 数字n和k，可能非常大，到达10^12规模
// 结果可能更大，所以返回结果对1000000007取模
// 来自真实大厂笔试，没有在线测试，对数器验证
public class Code02_MaximumProduct {
    // 暴力递归
    // 为了验证
    public static int maxValue1(int n, int k) {
        return f1(n, k);
    }

    // 剩余的数字rest拆成k份
    // 返回最大乘积
    // 暴力尝试一定能得到最优解
    public static int f1(int rest, int k) {
        if (k == 1) {
            return rest;
        }
        int ans = Integer.MIN_VALUE;
        for (int cur = 1; cur <= rest && (rest - cur) >= (k - 1); cur++) {
            int curAns = cur * f1(rest - cur, k - 1);
            ans = Math.max(ans, curAns);
        }
        return ans;
    }

    // 看看 n/k 平均数，然后看看n%k 有几个数需要+1，这样得到的结果就是最大的
    public static int maxValue2(int n, int k) {
        int mod = 1_000_000_007;
        long a = n / k;
        int b = n % k;
        long p1 = power(a, k - b, mod);
        long p2 = power(a + 1, b, mod);
        return (int) ((p1 * p2) % mod);
    }

    public static long power(long x, int n, int mod) {
        long ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = (ans * x) % mod;
            }
            x = (x * x) % mod;
            n >>= 1;
        }
        return ans;
    }
}
