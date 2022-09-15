package custom.code_2022_07;

/**
 * @ClassName LeetCode_233
 * @Author Duys
 * @Description
 * @Date 2022/7/13 13:34
 **/
// 233. 数字 1 的个数
// 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
public class LeetCode_233 {

    public static int countDigitOne(int n) {
        if (n < 1) {
            return 0;
        }
        // 获取当前n有几位数字
        int len = getLenOfNum(n);
        if (len == 1) {
            return 1;
        }
        // 13456
        // 10000
        int tmp1 = powerBaseOf10(len - 1);
        int first = n / tmp1;
        // 如果最高位是1，那么需要+1，否则不需要
        int firstOneNum = first == 1 ? n % tmp1 + 1 : tmp1;
        // 除去最高位，剩下数
        int otherOneNum = first * (len - 1) * (tmp1 / 10);
        return firstOneNum + otherOneNum + countDigitOne(n % tmp1);
    }

    public static int powerBaseOf10(int base) {
        return (int) Math.pow(10, base);
    }

    public static int getLenOfNum(int num) {
        int len = 0;
        while (num != 0) {
            num /= 10;
            len++;
        }
        return len;
    }

    /*public static int countDigitOne1(int n) {
        if (n < 1) {
            return 0;
        }
        return (int) km(n, 10);
    }

    // 题目的变种 k进制
    // 二分答案
    public static long km(int n, int k) {
        int len = bits(n, k);
        long l = 1;
        long r = power(k, len + 1);
        long ans = 0;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            if (onces(mid, k) >= n) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public static long onces(long n, int k) {
        int len = bits(n, k);
        if (len <= 1) {
            return len;
        }
        long offset = power(k, len - 1);
        long first = n / offset;
        long firstOnce = first == 1 ? (n % offset) + 1 : offset;
        long otherOnce = first * (len - 1) * (offset % k);
        return firstOnce + otherOnce + onces(n % offset, k);
    }

    public static int bits(long n, int k) {
        int len = 0;
        while (n != 0) {
            len++;
            n /= k;
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
    }*/

    public static void main(String[] args) {
        int n = 11;
        System.out.println(countDigitOne(n));
    }
}
