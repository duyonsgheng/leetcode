package week.week_2022_06_02;

/**
 * @ClassName Code_04_LeetCode_829
 * @Author Duys
 * @Description
 * @Date 2022/6/16 11:19
 **/
//829. 连续整数求和
// 给定一个正整数 n，返回 连续正整数满足所有数字之和为 n 的组数 。
public class Code_04_LeetCode_829 {

    // 连续的数字相加 = n
    // (x+0) + (x+1) + (x+2) +....+ (x+k)
    // 等差数列 n = k*x + k(k+1)/2 -> 2n = 2kx + k*k + k -> 2n = k(2x+k+1)
    // 2n = k(2x+k+1) -> 左边是偶数，那么右边一定是偶数和偶数 或者偶数和奇数，
    // 如果k为偶数，那么2x + k + 1就是奇数
    // 如果k为奇数，那么2x + k + 1就是偶数
    // 也就是说，对于每一种方案，k和2x + k + 1，一定是不同的，并且连奇偶性都相反
    // 所以2N里任何一个奇数因子，可能作为k这一项，也可能作为2x+k+1这一项，
    // 不管奇数因子作为哪一项，都可以推出另外一项的值，进而确定k和x具体是多少
    // 进而可以推出，2N里有多少个奇数因子，就有多少种方案
    // 于是这个题就变成了求N里有多少奇数因子
    // 一般来说，求N里有多少奇数因子，用O(根号N)的方法肯定可以
    public static int consecutiveNumbersSum(int n) {
        while ((n & 1) == 0) {
            n >>= 1;
        }
        int res = 1;
        // 只看奇数
        for (int i = 3; i * i <= n; i += 2) {
            int count = 1;
            while (n % i == 0) {
                n /= i;
                count++;
            }
            res *= count;
        }
        return n == 1 ? res : res << 1;
    }
}
