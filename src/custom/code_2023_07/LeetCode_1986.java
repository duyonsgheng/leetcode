package custom.code_2023_07;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1986
 * @date 2023年07月24日
 */
// 1986. 完成任务的最少工作时间段
// https://leetcode.cn/problems/minimum-number-of-work-sessions-to-finish-the-tasks/
public class LeetCode_1986 {
    // 3,1,3,1,1
    // 1 1 1 3 3  - 8
    public static int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length, m = 1 << n;
        int INF = 40;
        int[] dp = new int[m];
        Arrays.fill(dp, INF);
        for (int i = 1; i < m; i++) {
            int cur = i, index = 0;
            int spend = 0;
            // 每一个状态的子状态
            while (cur > 0) {
                int bit = cur & 1;
                if (bit == 1) {
                    spend += tasks[index];
                }
                cur >>= 1;
                index++;
            }
            if (spend <= sessionTime) {
                dp[i] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            if (dp[i] == 1) {
                continue;
            }
            // 是当前i的子集
            int spilt = i >> 1;
            // 这事当前i的子集的补集
            for (int j = (i - 1) & i; j > spilt; j = (j - 1) & i)
                dp[i] = Math.min(dp[i], dp[j] + dp[i ^ j]);
        }
        return dp[m - 1];
    }

    public static void main(String[] args) {
        //System.out.println(minSessions(new int[]{3, 1, 1, 3, 1}, 8));
        // [7,4,3,8,10]
        //12
        System.out.println(minSessions(new int[]{7, 4, 3, 8, 10}, 12));
    }
}
