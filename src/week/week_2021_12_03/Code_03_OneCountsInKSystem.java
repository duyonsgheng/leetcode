package week.week_2021_12_03;

/**
 * @ClassName Code_03
 * @Author Duys
 * @Description
 * @Date 2022/4/14 17:17
 **/
// k进制下1~m中数字1出现的次数，记作F(m,k)。例如F(5,3) = 5，因为三进制1~5为{1,2,10,11,12}，数字1出现了5次。
// 牛牛现在给你k和n，他想知道F(m,k)>=n，最小的m是多少呢。请你返回m的值。
// 核心方法，在大厂刷题班19节，第3题
public class Code_03_OneCountsInKSystem {

    public static long minM(int n, int k) {

        // 二分答案
        int len = bits(n, k);
        long l = 1;
        // 算一下总共的多大，定一个上界
        long r = power(k, len + 1);
        long ans = 0;
        // 去二分
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            if (ones(mid, k) >= n) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    // 搞出这个数字的k进制位有多少位
    public static int bits(long sum, int k) {
        int len = 0;
        while (sum != 0) {
            len++;
            sum /= k;
        }
        return len;
    }

    public static long power(long base, long power) {
        long ans = 1;
        while (power != 0) {
            if ((power & 1) != 0) {
                ans *= base;
            }
            base *= base;
            power >>= 1;
        }
        return ans;
    }

    public static long ones(long num, int k) {
        // 依然是拿到当前数的k进制位数
        int len = bits(num, k);
        // 如果位数是1 ，那么只含有一个1了
        if (len <= 1) {
            return len;
        }
        // 除去最高位上，剩下的多少，需要进行划分了
        // 比如 1234 len = 4
        // 算一个 k^(len-1)
        long offset = power(k, len - 1);
        // 把最高位拿出来
        long first = num / offset;
        // 如果最高位是1，那么当前总共多少个数字，就是 234+1 ，
        // 如果最高位不是1 是3，那么当前最高位是1，总共有多少个数字，就是 k^(len-1)
        long curOne = first == 1 ? (num % offset) + 1 : offset;
        // 那么就是
        // 235 - 1234
        // 1235 - 2234
        // 2235 - 3234
        long restOne = first * (len - 1) * (offset / k);
        return curOne + restOne + ones(num % offset, k);
    }
}
