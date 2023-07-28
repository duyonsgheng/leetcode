package custom.code_2023_07;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2017
 * @date 2023年07月27日
 */
// 2017. 网格游戏
// https://leetcode.cn/problems/grid-game/
public class LeetCode_2017 {
    public static long gridGame(int[][] grid) {
        int n = grid[0].length;
        long[][] dp = new long[2][n + 1];
        for (int i = 1; i <= n; i++) {
            // 每一行的前缀和
            dp[0][i] = dp[0][i - 1] + grid[0][i - 1];
            dp[1][i] = dp[1][i - 1] + grid[1][i - 1];
        }
        long ans = Long.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            // 第一个机器人一定是走最大的路线。然后拐弯。
            // 那么第二个机器人一定只能选择一条较小的路
            ans = Math.min(ans, Math.max(dp[0][n] - dp[0][i], dp[1][i - 1]));
        }
        return ans;
    }


    public static void main(String[] args) {
        // [[2,5,4],
        //  [1,5,1]]
        //  0 0 4
        //  1 0 0
        System.out.println(gridGame(new int[][]{{2, 5, 4}, {1, 5, 1}}));
    }
}
