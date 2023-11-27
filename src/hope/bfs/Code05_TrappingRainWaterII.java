package hope.bfs;

import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Code05_TrappingRainWaterII
 * @date 2023年11月23日 11:43
 */
// 二维接雨水
// 给你一个 m * n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度
// 请计算图中形状最多能接多少体积的雨水。
// 测试链接 : https://leetcode.cn/problems/trapping-rain-water-ii/
public class Code05_TrappingRainWaterII {
    // 从四周开始，
    // 找到薄弱点，然后从薄弱点开始一次把周围点拉进来
    public static int trapRainWater(int[][] height) {
        int[] move = {-1, 0, 1, 0, -1};
        int n = height.length;
        int m = height[0].length;
        // 0-行，1-列，2-水位限制是多少
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 边界
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    heap.add(new int[]{i, j, height[i][j]});
                    visited[i][j] = true;
                } else {
                    visited[i][j] = false;
                }
            }
        }
        int ans = 0;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            // 当前的水位限制是w，当前的高度是height[x][y]
            int x = cur[0], y = cur[1], w = cur[2];
            ans += w - height[x][y];
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i], ny = y + move[i + 1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                    heap.add(new int[]{nx, ny, Math.max(w, height[nx][ny])});
                    visited[nx][ny] = true;
                }
            }
        }
        return ans;
    }
}
