package week.week_2023_07_04;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName Code_03_LeetCode_2218
 * @date 2023年07月27日
 */
// https://leetcode.cn/problems/maximum-value-of-k-coins-from-piles/
public class Code_03_LeetCode_2218 {
    // 依然分组背包问题
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        // k就是背包容量，每一个硬币重量1，价值就是硬币的面值
        int[] dp = new int[k + 1];
        for (List<Integer> cur : piles) {
            // 背包剩余容量
            for (int rest = k; rest > 0; rest--) {
                // 容量和当前组的元素
                for (int i = 1, sum = 0; i <= Math.min(cur.size(), rest); i++) {
                    sum += cur.get(i - 1);
                    dp[rest] = Math.max(dp[rest], dp[rest - i] + sum);
                }
            }
        }
        return dp[k];
    }
}
