package week.week_2023_01_01;

/**
 * @ClassName Code_02_LeetCode_878
 * @Author Duys
 * @Description
 * @Date 2023/1/5 9:50
 **/
public class Code_02_LeetCode_878 {

    // 有点容斥的意思，一个数能被a整除或者被b整除，
    // 依然使用二分
    // 来到 m的时候，能整除a，或者整除b，然后求两个并集，减去既能整除a的又能整除b的
    public int nthMagicalNumber(int n, int a, int b) {
        int mod = 1_000_000_007;
        // 求a和b的最小公倍数
        long lcm = (long) a / gcd(a, b) * b;
        long ans = 0;
        // 右边界是 a b小的乘以n
        for (long l = 0, r = (long) n * Math.min(a, b), m = 0; l <= r; ) {
            m = (l + r) / 2;
            if (m / a + m / b - m / lcm >= n) {
                ans = m;
                r = m - 1;
            } else
                l = m + 1;
        }
        return (int) (ans % mod);
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
