package week.week_2023_02_02;

/**
 * @ClassName Code_01_LeetCode_920
 * @Author Duys
 * @Description
 * @Date 2023/2/9 9:15
 **/
// 920. 播放列表的数量
public class Code_01_LeetCode_920 {
    int mod = 1_000_000_007;
    int limit = 100;
    // 阶乘表
    long[] fac = new long[limit + 1];
    // 阶乘结果的乘法逆元
    long[] inv = new long[limit + 1];

    //方法1： 除法得逆元 + 容斥原理
    public int numMusicPlaylists(int n, int l, int k) {
        long cur, ans = 0, sign = 1;
        for (int i = 0; i <= n - k; i++, sign = sign == 1 ? (mod - 1) : 1) {
            // cur ->
            // fac[n] -> n! % mod 的结果！
            // inv[i] -> i! 的逆元！
            // inv[n - k - i] -> (n - k - i)! 的逆元
            // sign * 1 -> 1
            //      * -1 -> mod - 1
            cur = (sign * power(n - k - i, l - k)) % mod;
            cur = (cur * fac[n]) % mod;
            cur = (cur * inv[i]) % mod;
            cur = (cur * inv[n - k - i]) % mod;
            ans = (ans + cur) % mod;
        }
        return (int) ans;
    }

    public void init() {
        fac[0] = inv[0] = 1;
        for (int i = 1; i <= limit; i++) {
            fac[i] = ((long) i * fac[i - 1]) % mod;
        }
        // 阶乘的逆元倒推
        // 费马小定理
        inv[limit] = power(fac[limit], mod - 2);
        for (int i = limit; i > 1; i--) {
            inv[i - 1] = ((long) i * inv[i]) % mod;
        }
    }

    public long power(long x, int n) {
        long ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = (ans * x) % mod;
            }
            x = (x * x) % mod;
            n >>= 1;
        }
        return ans;
    }

    // 方法2：动态规划
    public int numMusicPlaylists1(int n, int l, int k) {
        // dp[i][j] 标识播放列表长度为i恰好包含j首不同的歌曲的数量
        long[][] dp = new long[l + 1][n + 1];
        // dp[i][j] 考虑最后一首歌，可以是播放过的，也可以是为播放过的
        // 如果为播放过的，那么就是dp[i][j]= dp[i-1][j-1] * (n-j+1) // 从剩下的歌曲中随意选择
        // 如果播放过，那么 dp[i][j]= dp[i-1][j] * max(j-k,0) j首歌，最近的k首不能播放
        dp[0][0] = 1;
        for (int i = 1; i <= l; i++) {
            for (int j = 1; j <= n; j++) {
                // 最后一首歌选择为播放的
                dp[i][j] += dp[i - 1][j - 1] * (n - j + 1);
                // 选择播放过的
                dp[i][j] += dp[i - 1][j] * Math.max(j - k, 0);
                dp[i][j] %= mod;
            }
        }
        return (int) dp[l][n];
    }
}
