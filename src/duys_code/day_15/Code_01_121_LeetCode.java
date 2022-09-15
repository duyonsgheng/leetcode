package duys_code.day_15;

/**
 * @ClassName Code_01_121_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/submissions/
 * @Date 2021/10/25 13:49
 **/
public class Code_01_121_LeetCode {
    /**
     * 给一个数组表示股票的某一天价格
     * [1,2,3,4,5]
     * 意思时 1 天1块买入。第5天5块卖出。最大利润是4块
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return -1;
        }
        int min = prices[0];
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }
}
