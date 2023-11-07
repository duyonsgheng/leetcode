package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2110
 * @date 2023年08月17日
 */
// 2110. 股票平滑下跌阶段的数目
// https://leetcode.cn/problems/number-of-smooth-descent-periods-of-a-stock/
public class LeetCode_2110 {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long ans = 1;
        int pre = 1; // 上一个
        // 3,2,1,4
        for (int i = 1; i < n; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                pre++;
            } else {
                pre = 1;
            }
            ans += pre;
        }
        return ans;
    }
}
