package custom.code_2023_05;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1335
 * @Author Duys
 * @Description
 * @Date 2023/5/16 10:17
 **/
// 1335. 工作计划的最低难度
public class LeetCode_1335 {
    // 动态规划
    // 1.工作必须顺序完成
    // 2.d天。，每一天的工作难度进行计算
    // 3.dp[i][2] = dp[j][1] 在0到i-1的最小值，和 jobDifficulty[j+1...i]最大值
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        int[][] dp = new int[n][d + 1];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        dp[0][1] = jobDifficulty[0]; // 第1天任务

        for (int i = 1; i < n; i++) {
            int[] max = new int[i + 1];
            max[i] = jobDifficulty[i];
            // 计算最大的值，倒序存着
            for (int k = i - 1; k >= 0; k--) {
                max[k] = Math.max(max[k + 1], jobDifficulty[k]);
            }
            for (int j = Math.min(d, i + 1); j > 1; j--) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[k][j - 1] + max[k + 1]);
                }
            }
            dp[i][1] = max[0];
        }
        return dp[n - 1][d];
    }
}
