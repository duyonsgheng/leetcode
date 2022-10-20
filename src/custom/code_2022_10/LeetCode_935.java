package custom.code_2022_10;

/**
 * @ClassName LeetCode_935
 * @Author Duys
 * @Description
 * @Date 2022/10/18 16:25
 **/
// 935. 骑士拨号器
public class LeetCode_935 {
    int mod = 1_000_000_007;
    //  move[num][time] 每个数字可以去的位置
    //  例如
    //  0 可以到 4 6位置
    //  1 可以去 6 8位置
    // 第i次来到num的时候，只需要加上i-1次来到num的时候总和
    int[][] move = new int[][]{{4, 6}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0}, {}, {1, 7, 0}, {2, 6}, {1, 3}, {2, 4}};

    public int knightDialer(int n) {
        // 底n-1次跳跃 可以去到数字 num时候，组成的不同号码数
        int[][] dp = new int[n][10];
        for (int num = 0; num < 10; num++) {
            dp[0][num] = 1;// 第0次跳到num的组成不同号码个数都是1
        }
        for (int time = 1; time < n; time++) {
            for (int num = 0; num < 10; num++) {
                // time能跳到num时候，那么time-1 能到num的所有数的总和
                for (int next : move[num]) {
                    dp[time][num] = (dp[time][num] + dp[time - 1][next]) % mod;
                }
            }
        }
        int ans = 0;
        for (int num = 0; num < 10; num++) {
            ans = (ans + dp[n - 1][num]) % mod;
        }
        return ans;
    }
}
