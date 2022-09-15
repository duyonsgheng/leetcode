package duys_code.day_32;

/**
 * @ClassName Code_10_204_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/count-primes/
 * @Date 2021/12/6 11:23
 **/
public class Code_10_204_LeetCode {

    public int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        // 偶数都不要
        boolean[] f = new boolean[n];
        int count = n / 2;
        for (int i = 3; i * i < n; i += 2) {
            if (f[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += 2 * i) {
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
        return count;
    }
}
