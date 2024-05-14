package hope.class82_enum1;

/**
 * @author Mr.Du
 * @ClassName Code05_Stack5
 * @date 2024年05月09日 上午 11:05
 */
// 买卖股票的最佳时机含手续费
// 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格
// 整数 fee 代表了交易股票的手续费用
// 你可以无限次地完成交易，但是你每笔交易都需要付手续费
// 如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
// 返回获得利润的最大值
// 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费
// 测试链接 : https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
public class Code05_Stack5 {
    public static int maxProfit(int[] prices, int fee) {
        // prepare : 交易次数无限制的情况下，或者收益的同时扣掉了一次购买的费用和手续费用后，最好的情况。
        int prepare = -prices[0] - fee;
        // done : 交易次数无限制的情况下，能获得的最大收益
        int done = 0; // 初次的时候没有收益
        for (int i = 1; i < prices.length; i++) {
            done = Math.max(done, prepare + prices[i]); // 当前卖出和当前不卖
            prepare = Math.max(prepare, done - prices[i] - fee); // 当前买入或者不买
        }
        return done;
    }
}
