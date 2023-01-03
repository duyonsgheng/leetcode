package week.week_2022_12_04;

/**
 * @ClassName Code_03_LeetCode_847
 * @Author Duys
 * @Description
 * @Date 2022/12/29 10:57
 **/
// 847. 访问所有节点的最短路径
public class Code_03_LeetCode_847 {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int[][] dist = floyd(graph, n);
        int ans = Integer.MAX_VALUE;
        int[][] dp = new int[1 << n][n];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++)
                dp[i][j] = -1;
        }
        for (int i = 0; i < n; i++) {
            // 从不同的城市出发，得到最小的值返回
            ans = Math.min(ans, process(1 << i, i, n, dist, dp));
        }
        return ans;
    }

    // 已经去过哪些城市了，记录再status里面
    // 当前处在那一座城，
    // 城市到城市的最短距离都在dist中
    public int process(int status, int cur, int n, int[][] dist, int[][] dp) {
        // 所有的城市都去过了
        if (status == (1 << n) - 1) {
            return 0;
        }
        if (dp[status][cur] != -1) {
            return dp[status][cur];
        }
        int ans = Integer.MAX_VALUE;
        for (int next = 0; next < n; next++) {
            // next还没去过，并且cur到next有路，就可以去
            if ((status & (1 << next)) == 0 && dist[cur][next] != Integer.MAX_VALUE) {
                int nextAns = process((status | (1 << next)), next, n, dist, dp);
                if (nextAns != Integer.MAX_VALUE) {
                    ans = Math.min(ans, dist[cur][next] + nextAns);
                }
            }
        }
        dp[status][cur] = ans;
        return ans;
    }

    public int[][] floyd(int[][] graph, int n) {
        int[][] dist = new int[n][n];
        // 初始化 ，都没路
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        // 自己到自己的路为0
        for (int i = 0; i < n; i++)
            dist[i][i] = 0;
        for (int cur = 0; cur < n; cur++) {
            for (int next : graph[cur]) {
                dist[next][cur] = 1;
                dist[cur][next] = 1;
            }
        }
        for (int jump = 0; jump < n; jump++) {
            for (int from = 0; from < n; from++) {
                for (int to = 0; to < n; to++) {
                    if (dist[from][jump] != Integer.MAX_VALUE && dist[jump][to] != Integer.MAX_VALUE
                            && dist[from][to] > dist[from][jump] + dist[jump][to]) {
                        dist[from][to] = dist[from][jump] + dist[jump][to];
                    }
                }
            }
        }
        return dist;
    }
}
