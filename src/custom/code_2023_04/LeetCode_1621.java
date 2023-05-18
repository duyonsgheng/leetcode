package custom.code_2023_04;

/**
 * @ClassName LeetCode_1621
 * @Author Duys
 * @Description
 * @Date 2023/4/19 10:45
 **/
// 1621. 大小为 K 的不重叠线段的数目
public class LeetCode_1621 {

    int mod = 1_000_000_007;

    public int numberOfSets1(int n, int k) {
        long[][] dp = new long[k + 1][n + 1];
        long[][] sum = new long[k + 1][n + 1];
        for (int j = 1; j <= n; j++) {
            dp[1][j] = dp[1][j - 1] + j - 1;
            sum[1][j] = sum[1][j - 1] + dp[1][j];
            dp[1][j] %= mod;
            sum[1][j] %= mod;
        }
        for (int i = 2; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1] + sum[i - 1][j - 1];
                sum[i][j] = sum[i][j - 1] + dp[i][j];
                dp[i][j] %= mod;
                sum[i][j] %= mod;
            }
        }
        return (int) dp[k][n];
    }

    public int numberOfSets(int n, int k) {
        int[] dp = new int[n];
        int[] sums = new int[n];
        dp[0] = sums[0] = 1;
        // 线段数
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                if (j < i) {
                    dp[j] = 0;
                } else if (i == j || i == 0) {
                    dp[j] = 1;
                } else {
                    dp[j] = (sums[j - 1] + dp[j - 1]) % mod;
                }
            }
            sums[0] = dp[0];
            for (int j = 1; j < n; j++) {
                sums[j] = (sums[j - 1] + dp[j]) % mod;
            }
        }
        return dp[n - 1];
    }

    // 数学
    // 要选择k条线段，如果线段首尾不重合，那么就是C n 2k,但是现在线段首尾可以重合，
    public int numberOfSets2(int n, int k) {
        return (int) C_n_2k(n + k - 1, k * 2, 1_000_000_007);
    }

    public long C_n_2k(int n, int m, int p) {
        long[] r = new long[m + 1];
        r[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
                r[j] = r[j - 1] + r[j];
                if (r[j] > p) {
                    r[j] %= p;
                }
            }
        }
        return r[m];
    }

    // 费马小定理
    // 求 C n m = m!/(n-m)!*m!
    // 定义：如果a*b = 1(mod m) ，那么b是a对模m的逆元，记作 b= a^(-1)(mod m)
    // 费马小定理：对于整数a和质数p，若a与p互质，则有a^(p-1)=1(mod p)
    public static int numberOfSets3(int n, int k) {
        return c_fermat(n + k - 1, k * 2, 1000000007); // 余数
    }

    // 利用费马小定理求C(n, m)
    private static int c_fermat(int n, int m, int p) {
        // C(n, m) = a / b
        long a = multiMod(n, n - m + 1, p); // 分式上面的部分
        long b = multiMod(m, 1, p); // 分式下面的部分
        // 推论2： a/b ≡ a*b^(p-2) (mod p)
        return (int) (a * firstPowMod(b, p - 2, p) % p);
    }

    // = n * (n-1) * (n-2) * ... * m
    private static long multiMod(int n, int m, int p) {
        long r = 1;
        for (int i = m; i <= n; i++) {
            r = r * i;
            if (r > p) r %= p;
        }
        return r;
    }

    // 快速幂算法
    // a^(2x) = (a^2)^x
    private static long firstPowMod(long a, int pow, int p) { // a^pow % p 快速幂算法
        long r = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) { // a^(2x+1) = a*a^(2x)
                r = (r * a) % p;
            }
            a = a * a % p;
            pow >>= 1;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(numberOfSets3(4, 2));
    }
}
