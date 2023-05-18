package custom.code_2023_04;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1615
 * @Author Duys
 * @Description
 * @Date 2023/4/18 15:27
 **/
// 1615. 最大网络秩
public class LeetCode_1615 {
    public int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] connect = new boolean[n][n];
        // 每个点的度
        int[] degree = new int[n];
        for (int[] road : roads) {
            int x = road[0], y = road[1];
            connect[x][y] = true;
            connect[y][x] = true;
            degree[x]++;
            degree[y]++;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int cur = degree[i] + degree[j] - (connect[i][j] ? 1 : 0);
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }
}
