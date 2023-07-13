package custom.code_2023_06;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1922
 * @date 2023年06月30日
 */
// 1922. 统计好数字的数目
// https://leetcode.cn/problems/count-good-numbers/
public class LeetCode_1922 {
    int mod = 1_000_000_007;

    // 数位dp的题
    public int countGoodNumbers(long n) {
        long a = (n >> 1); // 奇数位置个数
        long b = a; // 偶数位置的个数
        if ((n & 1) == 1) {
            b++;
        }
        return (int) (pow(4, a) * pow(5, b) % mod);
    }

    public long pow(long basic, long n) {
        if (n == 0) {
            return 1;
        }
        long a = pow(basic, n >> 1) % mod;
        long b = (a * a) % mod;
        return (n & 1) == 0 ? b : b * basic % mod;
    }

}
