package custom.code_2022_11;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1091
 * @Author Duys
 * @Description
 * @Date 2022/11/8 15:19
 **/
// 1091. 二进制矩阵中的最短路径
public class LeetCode_1091 {
    static int[][] dis = new int[][]{
            {0, 1}, {0, -1}, {1, 0}, {-1, 0},
            {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

    // 最短路径
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.add(new int[]{0, 0, 1});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int cost = poll[2];
            if (visited[x][y]) {
                continue;
            }
            if (x == n - 1 && y == n - 1) {
                return cost;
            }
            visited[x][y] = true;
            // 八个方向走去吧
            for (int i = 0; i < 8; i++) {
                int nx = x + dis[i][0];
                int ny = y + dis[i][1];
                // 走去吧
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && grid[nx][ny] == 0) {
                    queue.add(new int[]{nx, ny, cost + 1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(shortestPathBinaryMatrix(grid));
    }
}
