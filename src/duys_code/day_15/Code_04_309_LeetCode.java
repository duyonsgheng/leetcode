package duys_code.day_15;

/**
 * @ClassName Code_04_309_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * @Date 2021/10/26 11:07
 **/
public class Code_04_309_LeetCode {
    // 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格
    // 卖出后有一个冻结期。
    public static int maxProfit1(int[] prices) {
        return process1(prices, 0, false, 0);
    }

    // 先来憋一个尝试
    // index - 当前来到的位置，
    // choose - 之前做的决定，是买入还是卖出
    // choose - true ，已经买了，买入价格是 preBuy
    // choose - false，不需要买，而且当前是可以参与交易的，
    // preBuy - 之前买的话，买入价是多少
    public static int process1(int[] arr, int index, boolean choose, int preBuy) {
        // index 可能会到大于了arr.length位置。因为我最后一个位置买了。那么卖不出去
        if (index >= arr.length) {
            return 0;
        }
        // 之前是买入的
        if (choose) {
            // index 不卖
            int noSell = process1(arr, index + 1, true, preBuy);
            //  index 卖出
            int yesSell = arr[index] - preBuy + process1(arr, index + 2, false, 0);
            return Math.max(noSell, yesSell);
        } else {
            // index 不买
            int noBuy = process1(arr, index + 1, false, 0);
            // index 买
            int yesBuy = process1(arr, index + 1, true, arr[index]);
            return Math.max(noBuy, yesBuy);
        }
    }

    // buy[i] 是 0~i范围上最后一次操作是 buy 购买，最好的收益
    // sell[i] 是0~i范围上最后一次操作是sell 卖出 ，最好的收益
    public static int maxProfit2(int[] prices) {
        // 如果i位置上是没有发生购买行为 说明 就是i-1的答案
        // 如果i位置上发生了后买行为 那就是sell[i-2] - prices[i] -> 因为i位置是购买，那么i-1位置是冷冻期。所以是i-2位置上卖出的最好收益-当前i位置的值
        // buy[i] = max(buy[i-1],sell[i-2]-prices[i]);

        // sell[i]
        // 在i位置是卖出操作
        // i位置不卖
        // sell[i] = sell[i-1]
        // i位置卖出了，buy[i-1]+prices[i]
        if (prices.length < 2) {
            return 0;
        }
        int N = prices.length;
        int[] buy = new int[N];
        int[] sell = new int[N];
        // 1位置是buy操作
        buy[1] = Math.max(0 - prices[0], 0 - prices[1]);
        sell[1] = Math.max(0, prices[1] - prices[0]);
        for (int i = 2; i < N; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[N - 1];
    }
}
