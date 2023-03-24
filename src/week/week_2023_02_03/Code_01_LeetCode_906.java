package week.week_2023_02_03;

/**
 * @ClassName Code_01_LeetCode_906
 * @Author Duys
 * @Description
 * @Date 2023/2/16 12:54
 **/
// 906. 超级回文数
public class Code_01_LeetCode_906 {
    // 根据题意给出的数据量，我们可以推断出这个题需要定一个范围，
    // 10^18次方的数，开方后10^9次方，那么我们看10^9次方内是回文数，
    // 那么就要把数分为两半，然后变成回文，可以是奇数长度回文，可以数偶数长度回文，那么基数已经定下来了，再10^5次方内。
    public static int superpalindromesInRange(String left, String right) {
        long l = Long.valueOf(left), r = Long.valueOf(right);
        long limit = (long) Math.sqrt((double) r);
        int cnt = 0;
        long mode = 1;
        long num = 0;
        do {
            // 偶数的
            num = even(mode);
            if (isValid(num * num, l, r)) {
                cnt++;
            }
            num = odd(mode);
            if (isValid(num * num, l, r)) {
                cnt++;
            }
            mode++;
        } while (num < limit);
        return cnt;
    }

    // 根据基数拼接奇数长度回文
    public static long odd(long mode) {
        long ans = mode;
        mode /= 10;
        while (mode != 0) {
            ans = ans * 10 + mode % 10;
            mode /= 10;
        }
        return ans;
    }

    // 根据基数拼接偶数长度回文
    public static long even(long mode) {
        long ans = mode;
        while (mode != 0) {
            ans = ans * 10 + mode % 10;
            mode /= 10;
        }
        return ans;
    }

    public static boolean isValid(long ans, long l, long r) {
        return isPalindrome(ans) && ans >= l && ans <= r;
    }

    public static boolean isPalindrome(long n) {
        // 确定offest
        long offset = 1;
        while (n / offset >= 10) {
            offset *= 10;
        }
        // 比较首位数字是否相等，每次去掉首位继续比较
        while (n != 0) {
            // 首位数字比较
            if (n / offset != n % 10) {
                return false;
            }
            // 去掉首位
            n = (n % offset) / 10;
            // 每次减少了两个数字。offset/100
            offset /= 100;
        }
        return true;
    }
}
