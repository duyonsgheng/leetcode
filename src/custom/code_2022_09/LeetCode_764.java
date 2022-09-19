package custom.code_2022_09;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_764
 * @Author Duys
 * @Description
 * @Date 2022/9/19 16:11
 **/
// 764. 最大加号标志
public class LeetCode_764 {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] dp = new int[n + 1][n + 1];
        // 标记哪些0点
        Set<Integer> set = new HashSet<>();
        for (int[] m : mines) {
            set.add(m[0] * n + m[1]);
        }
        int ans = 0;
        int count = 0;
        // 0 1 1 1 0 1 1 0 1 1
        // 0 1 2 3 0 1 2 0 1 2
        // 0 3 2 1 0 2 1 0 2 1
        // 左右来一遍可以找到一行中的中位数，半径
        // 行计算
        for (int row = 0; row < n; row++) {
            count = 0;
            for (int col = 0; col < n; col++) {
                count = set.contains(row * n + col) ? 0 : count + 1;
                dp[row][col] = count;
            }
            count = 0;
            for (int col = n - 1; col >= 0; col--) {
                count = set.contains(row * n + col) ? 0 : count + 1;
                dp[row][col] = Math.min(dp[row][col], count);
            }
        }
        // 列计算
        // 找到列中的半径
        for (int col = 0; col < n; col++) {
            count = 0;
            for (int row = 0; row < n; row++) {
                count = set.contains(row * n + col) ? 0 : count + 1;
                dp[row][col] = Math.min(dp[row][col], count);
            }
            count = 0;
            for (int row = n - 1; row >= 0; row--) {
                count = set.contains(row * n + col) ? 0 : count + 1;
                dp[row][col] = Math.min(dp[row][col], count);
                ans = Math.max(ans, dp[row][col]);
            }
        }
        return ans;
    }
}
