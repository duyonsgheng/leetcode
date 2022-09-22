package custom.code_2022_09;

/**
 * @ClassName LeetCode_790
 * @Author Duys
 * @Description
 * @Date 2022/9/20 15:02
 **/
// 790. 多米诺和托米诺平铺
public class LeetCode_790 {
    int mod = 1000000007;

    // 第一眼看来就是一个状态dp，但是两个state的dp有些复杂，想其他办法

    public int numTilings(int n) {
        int[] dp = new int[n + 3]; // 2n行的数据状况
        // dp2[] 表示2n+1这种情况 ，因为托米了是奇数的。始终都会多一块
        //1. 前面数竖着的
        // x ?
        // x ?
        // 2.前面是横着的
        // x x ?
        // x x ?
        // 1.当多米罗的情况下，当前可以是来自之前的平铺和竖着 那么就是dp[i] = dp[i-1]+dp[i-2]
        // x x ?    x  ?  ?
        // x ? ? 和 x  x  ?  这种情况是  dp[i] = 2*dp2[i-2]
        // dp2[i-2] -> dp[i-3]+dp2[i-3]
        // dp[i] = 2*dp[i-1]+dp[i-2] + 2*(dp[i-3]+dp2[i-3]) - dp[i-1]
        // dp[i-1] = dp[i-2]+dp[i-3]+2*dp2[i-3]
        // dp[i] = 2*dp[i-1]+dp[i-3]
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        for (int i = 3; i <= n; i++) {
            dp[i] = ((2 * dp[i - 1] % mod) % mod + dp[i - 3] % mod) % mod;
        }
        return dp[n];
    }
}
