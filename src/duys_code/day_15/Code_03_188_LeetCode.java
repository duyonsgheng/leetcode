package duys_code.day_15;

/**
 * @ClassName Code_03_188_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 * @Date 2021/10/25 15:55
 **/
public class Code_03_188_LeetCode {

    /**
     *给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
     */

    /**
     * 如果 k >= N/2 了，那么就会存在无限次交易。就变成了股票问题2的解法了、把每一次上升的差值累加起来
     * 如果 k <   N/2 那么就是一个从左往右的尝试模型 dp含义 dp[i][j] 是 0到i范围上，不超过j次交易，最多的利润
     * 可能性分析：例如dp[8][3]
     * 1.第8位置直接不参与，那么就是dp[7][3]的值
     * 2.第8位置参与
     * ---2.1 8位置作为最后一次交易，8位置必须是卖出。如果8位置是买入和卖出 那么 就是dp[8][2] + price[8] - price[8]
     * ---2.2 8位置作为最后一次交易，8位置必须是卖出。如果8位置是卖出 。如果 price[8] > price[7] 存在 dp[7][2] +  price[8] - price[7]
     * .....
     * 所以
     * dp[8][3]  = Math.max();
     * ---------p1 = dp[8][2] + price[8] - price[8]
     * ---------p2 = dp[7][2] + price[8] - price[7]
     * ---------p3 = dp[6][2] + price[8] - price[6]
     * ............
     * ---------pN = dp[0][2] + price[8] - price[0]
     * 所有可能性中求最大得
     * 最后再和dp[7][3] 比较。取最大的
     * <p>
     * <p>
     * 斜率优化
     * dp[7][3] ..
     * p1 = dp[7][2] + price[7] - price[7]
     * p2 = dp[6][2] + price[7] - price[6]
     * p3 = dp[5][2] + price[7] - price[5]
     * .........
     * pN = dp[0][2] + price[7] - price[0]
     * <p>
     * 观察可以得到
     * 我们把 price[8] 提出来。最后决策出最大的后，统一加上 price[i]
     * 那么我们可以得到 dp[i][j] =
     * p1 = dp[i-1][j-1] - price[i-1] + price[i]
     * p2 = dp[i][j-1]
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 1 || k < 1) {
            return 0;
        }
        int N = prices.length;
        if (k >= N / 2) {
            return all(prices);
        }
        int[][] dp = new int[N][k + 1];

        // 一列一列的填。
        for (int j = 1; j <= k; j++) {
            // dp[1][j]
            // dp[0][j]
            int p1 = dp[0][j];
            //int p2 = dp[1][j - 1] + prices[1] - prices[1];
            //int p3 = dp[0][j - 1] + prices[1] - prices[0];
            int pre = Math.max(dp[1][j - 1] - prices[1], dp[0][j - 1] - prices[0]);
            dp[1][j] = Math.max(p1, pre + prices[1]);
            // 每一行，每一行的往下填
            for (int i = 2; i < N; i++) {
                // i位置不参与
                p1 = dp[i - 1][j];
                // i位置参与
                int best = dp[i][j - 1] - prices[i];
                pre = Math.max(pre, best);
                dp[i][j] = Math.max(p1, pre + prices[i]);
            }
        }
        return dp[N - 1][k];
    }

    public static int all(int[] arr) {
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            ans += Math.max(arr[i] - arr[i - 1], 0);
        }
        return ans;
    }

}
