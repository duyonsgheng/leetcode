package hope.class82_enum1;

/**
 * @author Mr.Du
 * @ClassName Code06_Stack6
 * @date 2024年05月09日 下午 03:00
 */
// 买卖股票的最佳时机含冷冻期
// 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格
// 设计一个算法计算出最大利润
// 在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
// 测试链接 : https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
public class Code06_Stack6 {
    public static int maxProfit1(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        //prepare[i] : 0...i范围上，可以做无限次交易，获得收益的同时一定要扣掉一次购买，所有情况最好的情况
        int[] prepare = new int[n];
        //done[i] : 0...i范围上，可以做无限次交易，最好的收益
        int[] done = new int[n];
        prepare[1] = Math.max(-prices[0], -prices[1]); // 在0和1位置的时候进行购买
        done[1] = Math.max(0, prices[1] - prices[0]);
        for (int i = 2; i < n; i++) {
            done[i] = Math.max(done[i - 1], prepare[i - 1] + prices[i]); // 选择当前位置卖出或者当前位置不卖出
            prepare[i] = Math.max(prepare[i - 1], done[i - 2] - prices[i]);// 选择当前位置买入，或者当前位置不买，如果当前位置买入的话，那么前一天i-1位置就是冷冻期
        }
        return done[n - 1];
    }

    // 把方法1变为几个变量滚动下去
    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        int prepare = Math.max(-prices[0], -prices[1]);
        // done[i-2]
        int done2 = 0;
        // done[i-1]
        int done1 = Math.max(0, prices[1] - prices[0]);
        for (int i = 2, cur; i < n; i++) {
            cur = Math.max(done1, prepare + prices[i]);
            prepare = Math.max(prepare, done2 - prices[i]);
            done2 = done1;
            done1 = cur;
        }
        return done1;
    }
}
