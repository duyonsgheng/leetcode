package custom.code_2023_05;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_1765
 * @Author Duys
 * @Description
 * @Date 2023/5/22 9:10
 **/
// 1765. 地图中的最高点
public class LeetCode_1765 {
    // 0 1
    // 0 0
    // 多源BFS，把所有的水域开始点
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[][] highestPeak(int[][] isWater) {
        Deque<int[]> queue = new LinkedList<>();
        int n = isWater.length, m = isWater[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isWater[i][j] == 1) {
                    queue.addLast(new int[]{i, j});
                    ans[i][j] = 0;
                } else ans[i][j] = -1;
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.pollFirst();
            int x = cur[0], y = cur[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= n || nx < 0 || ny >= m || ny < 0) {
                    continue;
                }
                if (ans[nx][ny] != -1) {
                    continue;
                }
                ans[nx][ny] = ans[x][y] + 1;
                queue.addLast(new int[]{nx, ny});
            }
        }
        return ans;
    }
}
