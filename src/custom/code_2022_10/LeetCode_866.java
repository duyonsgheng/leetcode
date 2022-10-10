package custom.code_2022_10;

/**
 * @ClassName LeetCode_866
 * @Author Duys
 * @Description
 * @Date 2022/10/9 13:48
 **/
public class LeetCode_866 {
    // 遍历所有的数字
    // 就暴力尝试，只是检查是不是素数的时候需要知道一个数是不是素数，看看开根号到2这之间有咩有数能整除
    // 检查是不是回文，检查是不是素数
    public int primePalindrome(int n) {
        while (true) {
            // 检查回文，检查素数
            if (n == reverse(n) && isPrime(n)) {
                return n;
            }
            n++;
            // 8位数，是没有素数的
            if (n > 10_000_000 && n < 100_000_000) {
                n = 100_000_000;
            }
        }
    }

    // 检查是不是素数
    // 对于 123 这个数来说，检查开根号后，有没有能被 123 整除的，有的话，就不是，检查2到11 能不能整除123
    public boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        int r = (int) Math.sqrt(n);
        for (int d = 2; d <= r; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return true;
    }

    // 数字反转 就是回文检查
    public int reverse(int n) {
        int ans = 0;
        while (n > 0) {
            ans = 10 * ans + (n % 10);
            n /= 10;
        }
        return ans;
    }

}
