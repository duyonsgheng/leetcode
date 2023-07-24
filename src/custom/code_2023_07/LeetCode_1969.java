package custom.code_2023_07;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1969
 * @date 2023年07月17日
 */
// 1969. 数组元素的最小非零乘积
// https://leetcode.cn/problems/minimum-non-zero-product-of-the-array-elements/
public class LeetCode_1969 {
    int mod = 1_000_000_007;

    public int minNonZeroProduct(int p) {
        long a1 = process(2, p) - 1;
        long a2 = process(process(2, p) - 2, (1l << (p - 1)) - 1);
        return (int) (a1 * a2 % mod);
    }

    public long process(long a, long k) {
        if (k == 0) {
            return 1;
        }
        if (k == 1) {
            return a;
        }
        long ans = process(a, k >> 1);
        ans = ans * ans % mod;
        if ((k & 1) == 0) {
            return ans;
        }
        return ans * a % mod;
    }
}
