package custom.code_2022_11;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1029
 * @Author Duys
 * @Description
 * @Date 2022/11/4 16:38
 **/
// 1029. 两地调度
public class LeetCode_1029 {

    public int twoCitySchedCost(int[][] costs) {
        //差值大的先处理
        Arrays.sort(costs, (a, b) -> Math.abs(b[0] - b[1]) - Math.abs(a[0] - a[1]));
        int n = costs.length;
        int a = n >> 1;
        int b = n >> 1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 都还有名额
            if (a > 0 && b > 0) {
                // 肯定去B啊
                if (costs[i][0] > costs[i][1]) {
                    b--;
                    ans += costs[i][1];
                } else { // 去A
                    a--;
                    ans += costs[i][0];
                }
            } else if (a > 0) {
                a--;
                ans += costs[i][0];
            } else {
                b--;
                ans += costs[i][1];
            }
        }
        return ans;
    }

    public int twoCitySchedCost1(int[][] costs) {
        int N = costs.length;
        int M = N >> 1;
        int[][] dp = new int[N + 1][M + 1];
        // 还剩下i个人未分配
        for (int i = N - 1; i >= 0; i--) {
            // 去往A地多少人
            for (int j = 0; j <= M; j++) {
                if (N - i == j) { // 剩下的司机全部都去A了。
                    dp[i][j] = costs[i][0] + dp[i + 1][j - 1];
                } else if (j == 0) {
                    dp[i][j] = costs[i][1] + dp[i + 1][j];
                } else {
                    int p1 = costs[i][0] + dp[i + 1][j - 1];
                    int p2 = costs[i][1] + dp[i + 1][j];
                    dp[i][j] = Math.min(p1, p2);
                }
            }
        }
        return dp[0][M];
    }
}
