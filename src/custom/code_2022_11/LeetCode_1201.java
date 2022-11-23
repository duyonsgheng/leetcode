package custom.code_2022_11;

/**
 * @ClassName LeetCode_1201
 * @Author Duys
 * @Description
 * @Date 2022/11/23 14:53
 **/
// 1201. 丑数 III
public class LeetCode_1201 {

    public int nthUglyNumber(int n, int a, int b, int c) {
        long lcm_ab = lcm(a, b);
        long lcm_ac = lcm(a, c);
        long lcm_bc = lcm(b, c);
        long lcm_abc = lcm(a, lcm(b, c));
        int min = Math.min(a, Math.min(b, c));

        long l = 0, r = min * n + 1;
        while (l + 1 < r) {
            long m = l + ((r - l) >> 1);
            long count = (m / a + m / b + m / c) + m / lcm_abc;
            count -= (m / lcm_ab + m / lcm_ac + m / lcm_bc);
            if (count < n) {
                l = m;
            } else {
                r = m;
            }
        }
        return (int) r;
    }

    // 求最小公倍数
    public static long lcm(long a, long b) {
        return a * b / (gcd(a, b));
    }

    // 求最大公约数
    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        int a = 4;
        int b = 6;
        long m = a + (b - a) >> 1;
        System.out.println(m);
    }
}
