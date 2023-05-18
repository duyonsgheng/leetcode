package duys_code.day_29;

/**
 * @ClassName Code_02_50_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/powx-n/
 * @Date 2021/11/25 13:24
 **/
public class Code_02_50_LeetCode {
    /**
     * 计算x的n次幂
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        double t = x;
        double ans = 1;
        while (pow != 0) {
            if ((pow & 1) != 0) {
                ans *= t;
            }
            t *= t;
            pow >>= 1;
        }
        if (n == Integer.MIN_VALUE) {
            ans *= x;
        }
        return n > 0 ? ans : 1 / ans;
    }

    // 快速幂 x的n次幂
    public static int pow(int x, int n) {
        int ans = 1;
        int t = x;
        while (n != 0) {
            if ((n & 1) != 0) {
                ans *= t;
            }
            t *= t;
            // n每次右移一位
            n >>= 1;
        }
        return ans;
    }
}
