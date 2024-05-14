package hope.class82_enum1;

/**
 * @author Mr.Du
 * @ClassName Code04_Stock4
 * @date 2024年04月19日 上午 09:35
 */
// 买卖股票的最佳时机 IV
// 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易
// 也就是说，你最多可以买 k 次，卖 k 次
// 注意：你不能同时参与多笔交易，你必须在再次购买前出售掉之前的股票
// 测试链接 : https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
public class Code04_Stock4 {
    public static int maxProfit1(int k, int[] prices) {
        int n = prices.length;
        if (k >= n / 2) { // 就是所有的股票上坡都能抓到，也就是说交易次数没有限制，那么就是股票2问题
            return f1(prices);
        }
        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1];
                for (int p = 0; p < j; p++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + prices[j] - prices[p]);
                }
            }
        }
        return dp[k][n - 1];
    }


    public static int maxProfit2(int k, int[] prices) {
        int n = prices.length;
        if (k >= n / 2) { // 就是所有的股票上坡都能抓到，也就是说交易次数没有限制，那么就是股票2问题
            return f1(prices);
        }
        int[][] dp = new int[k + 1][n];
        for (int i = 1, best; i <= k; i++) {
            best = dp[i - 1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[j][i - 1], best + prices[j]);
                best = Math.max(best, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][n - 1];
    }

    public static int f1(int[] arr) {
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            // 收集上坡的值
            ans += Math.max(arr[i] - arr[i - 1], 0);
        }
        return ans;
    }
}
