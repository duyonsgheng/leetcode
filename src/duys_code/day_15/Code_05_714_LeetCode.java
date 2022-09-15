package duys_code.day_15;

/**
 * @ClassName Code_05_714
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * @Date 2021/10/26 13:30
 **/
public class Code_05_714_LeetCode {

    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int N = prices.length;
        // 0位置必须要买入
        int bestBuy = 0 - prices[0] - fee;
        // 0位置必须卖出
        int bestSell = 0;
        for (int i = 1; i < N; i++) {
            // 当前位置必须买入
            int curBuy = bestSell - prices[i] - fee;
            // 当前位置必须要卖出
            int curSell = bestBuy + prices[i];
            bestBuy = Math.max(curBuy, bestBuy);
            bestSell = Math.max(curSell, bestSell);
        }
        return bestSell;
    }
}
