package duys_code.day_15;

/**
 * @ClassName Code_01_122_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 * @Date 2021/10/25 14:08
 **/
public class Code_02_122_LeetCode {
    /**
     * 股票问题2：在股票问题1中，针对只能交易一次。本题可以交易多次，但是已经交易过了，就不能再参与，而且一次只能持有一只股票
     * 思路：把整个数组的单调性画一下。用每次的波谷 到 波封的差值就是答案。然后累加。小于0的不加，大于0的就加上，其实也就是相当于把每一个波谷到波峰的差算出来了
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }
}
