package hope.class77_rangeDP2;

/**
 * @author Mr.Du
 * @ClassName Code05_MinimumCostToMergeStones
 * @date 2024年03月24日 20:57
 */
// 合并石头的最低成本
// 有 n 堆石头排成一排，第 i 堆中有 stones[i] 块石头
// 每次 移动 需要将 连续的 k 堆石头合并为一堆，而这次移动的成本为这 k 堆中石头的总数
// 返回把所有石头合并成一堆的最低成本
// 如果无法合并成一堆返回-1
// 测试链接 : https://leetcode.cn/problems/minimum-cost-to-merge-stones/
public class Code05_MinimumCostToMergeStones {

    // l....r这之间能合并成几份，其实是注定的
    // 左边一份，右边k-1份
    // 左边两份，右边k-2份
    // ....
    public static int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) {
            return -1;
        }
        int[] preSum = new int[n + 1];
        for (int i = 0, j = 1, sum = 0; i < n; i++, j++) {
            sum += stones[i];
            preSum[j] = sum;
        }
        // dp[l][r]含义，l...r上的石头合并到不能再合并了，最小的代价
        int[][] dp = new int[n][n];
        for (int l = n - 2, ans; l >= 0; l--) {
            for (int r = l + 1; r < n; r++) {
                ans = Integer.MAX_VALUE;
                // 左边是一定呈现为1 2 3 ....
                for (int m = l; m < r; m += k - 1) {
                    ans = Math.min(ans, dp[l][m] + dp[m + 1][r]);
                }
                if ((r - 1) % (k - 1) == 0) {
                    ans += preSum[r + 1] - preSum[l];
                }
                dp[l][r] = ans;
            }
        }
        return dp[0][n - 1];
    }
}
