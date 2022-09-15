package custom.code_2022_08;

/**
 * @ClassName LeetCode_526
 * @Author Duys
 * @Description
 * @Date 2022/8/22 13:38
 **/
// 526. 优美的排列
public class LeetCode_526 {

    // 1.dfs的解法
    public int countArrangement1(int n) {
        return dfs(n, 1, new boolean[n + 1]);
    }

    public static int dfs(int n, int cur, boolean[] visited) {
        if (cur > n) {
            // 找到了一种
            return 1;
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i] && (i % cur == 0 && cur % i == 0)) {
                visited[i] = true;
                ans += dfs(n, cur + 1, visited);
                visited[i] = false;
            }
        }
        return ans;
    }

    // 2.状态压缩
    public int countArrangement2(int n) {
        return dfs2(n, 1, 0);
    }

    public static int dfs2(int n, int cur, int status) {
        if (cur > n) {
            // 找到了一种
            return 1;
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (((1 << i) & status) == 0 && (i % cur == 0 || cur % i == 0)) {
                ans += dfs2(n, cur + 1, (status | (1 << i)));
            }
        }
        return ans;
    }

    // 3.傻缓存
    public int countArrangement3(int n) {
        int[][] dp = new int[n + 1][1 << n];
        return dfs3(n, 1, 0, dp);
    }

    public static int dfs3(int n, int cur, int status, int[][] dp) {
        if (cur > n) {
            // 找到了一种
            return 1;
        }
        if (dp[cur][status] != 0) {
            return dp[cur][status];
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (((1 << i) & status) == 0 && (i % cur == 0 || cur % i == 0)) {
                ans += dfs2(n, cur + 1, (status | (1 << i)));
            }
        }
        dp[cur][status] = ans;
        return ans;
    }

    // 4.状态压缩的dp
    public int countArrangement4(int n) {
        int status = 1 << n;
        int[][] dp = new int[n + 1][status];
        // 一个也不选
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // 所有状态
            for (int j = 0; j < status; j++) {
                for (int k = 1; k <= n; k++) {
                    // 当前k一定要被选择
                    if (((j >> (k - 1)) & 1) == 0) {
                        continue;
                    }
                    // k无效
                    if (k % i != 0 && i % k != 0) {
                        continue;
                    }
                    dp[i][j] += dp[i - 1][j & (~(1 << (k - 1)))];
                }
            }
        }
        return dp[n][status - 1];
    }

    // 5.状态压缩的dp
    public int countArrangement5(int n) {
        int status = 1 << n;
        int[] dp = new int[status];
        // 一个也不选
        dp[0] = 1;
        for (int visited = 0; visited < status; visited++) {
            //
            int bitOnces = Integer.bitCount(visited);
            for (int num = 1; num <= n; num++) {
                // 1.当前数还没被使用过
                if (((1 << (num - 1)) & visited) != 0 && (num % bitOnces == 0 || bitOnces % num == 0)) {
                    dp[visited] += dp[visited & (~(1 << (num - 1)))];
                }
            }
        }
        return dp[status - 1];
    }
}
