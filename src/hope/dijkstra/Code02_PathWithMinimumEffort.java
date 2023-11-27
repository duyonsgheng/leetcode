package hope.dijkstra;

import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Code02_PathWithMinimumEffort
 * @date 2023年11月27日 11:01
 */
// 最小体力消耗路径
// 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights
// 其中 heights[row][col] 表示格子 (row, col) 的高度
// 一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1)
// （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动
// 你想要找到耗费 体力 最小的一条路径
// 一条路径耗费的体力值是路径上，相邻格子之间高度差绝对值的最大值
// 请你返回从左上角走到右下角的最小 体力消耗值
// 测试链接 ：https://leetcode.cn/problems/path-with-minimum-effort/
public class Code02_PathWithMinimumEffort {
    public static int[] move = new int[]{-1, 0, 1, 0, -1};

    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int[][] distance = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        distance[0][0] = 0;
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        heap.add(new int[]{0, 0, 0});
        while (!heap.isEmpty()) {
            int[] poll = heap.poll();
            int r = poll[0];
            int c = poll[1];
            int w = poll[2];
            if (visited[r][c]) {
                continue;
            }
            visited[r][c] = true;
            if (r == n - 1 && c == m - 1) {
                return w;
            }
            for (int i = 0; i < 4; i++) {
                int nr = r + move[i];
                int nc = c + move[i + 1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc]) {
                    int nw = Math.max(w, Math.abs(heights[r][c] - heights[nr][nc]));
                    if (nw < distance[nr][nc]) {
                        distance[nr][nc] = nw;
                        heap.add(new int[]{nr, nc, nw});
                    }
                }
            }
        }
        return -1;
    }
}
