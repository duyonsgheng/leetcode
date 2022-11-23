package custom.code_2022_11;

/**
 * @ClassName LeetCode_1155
 * @Author Duys
 * @Description
 * @Date 2022/11/17 16:33
 **/
// 1155. 掷骰子的N种方法
public class LeetCode_1155 {
    int mod = 1_000_000_007;

    // 背包问题
    public int numRollsToTarget(int n, int k, int target) {

        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;// 只有一种
        // 枚举所有的骰子
        for (int i = 1; i <= n; i++) {
            // 枚举背包
            for (int m = 0; m <= target; m++) {
                // 枚举扔
                for (int c = 1; c <= k; c++) {
                    // 如果剩下的都不够了，不用在尝试了
                    if (m < c) {
                        continue;
                    }
                    // 当前容量 需要把少一个骰子，但是当前容量为 m-c所有的累加和
                    dp[i][m] = (dp[i][m] + dp[i - 1][m - c]) % mod;
                }
            }
        }
        return dp[n][target];
    }
}
