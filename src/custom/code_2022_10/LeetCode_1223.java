package custom.code_2022_10;

/**
 * @ClassName LeetCode_1223
 * @Author Duys
 * @Description
 * @Date 2022/10/20 15:07
 **/
// 1223. 掷骰子模拟
public class LeetCode_1223 {

    int mod = 1_000_000_007;

    // 投掷n次，点数不能超过rollMax[i] 次
    public int dieSimulator1(int n, int[] rollMax) {
        return process(1, 0, 0, n + 1, rollMax);
    }

    // 当前投递的是第几次 cur
    // 限制次数limit次
    // 上一次投递的是啥 pre，
    // 出现了几次了？ count
    public int process(int cur, int pre, int count, int limit, int[] arr) {
        if (cur == limit) {
            return 1;
        }
        int ans = 0;
        for (int i = 1; i <= 6; i++) {
            if (pre == i) {
                if (count + 1 <= arr[i - 1]) {
                    ans += process(cur + 1, i, count + 1, limit, arr);
                }
            } else {
                ans += process(cur + 1, i, 1, limit, arr);
            }
            ans %= mod;
        }
        return ans;
    }

    //求多少个值或最值问题，要么贪心、搜索、排列组合，要么DP。这里显然应当用dp
    public int dieSimulator(int n, int[] rollMax) {
        long[][][] dp = new long[n + 1][6][16]; // dp[i][j][k] 表示第i次扔，面是j，次数是k的有效扔的次数
        // 1.当k的次数超过了1 那么dp[i][j][k] = dp[i-1][j][k-1] 当前次也不能要了，
        // 2.k==1，把之前投递的次数，非j的全部转移过来
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == 1) {
                    dp[i][j][1] = 1;
                    continue;
                }
                for (int k = 1; k <= rollMax[j]; k++) {
                    if (k > 1) {
                        dp[i][j][k] = dp[i - 1][j][k - 1] % mod;
                    } else {
                        long sum = 0;
                        for (int p = 0; p < 6; p++) {
                            if (p == j) {
                                continue;
                            }
                            for (int m = 1; m <= rollMax[p]; m++)
                                sum = (sum + dp[i - 1][p][m]) % mod;
                        }
                        dp[i][j][k] = sum;
                    }
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j <= rollMax[i]; j++) {
                ans = (ans + dp[n][i][j]) % mod;
            }
        }
        return (int) ans;
    }
}
