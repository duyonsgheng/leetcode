package hope.dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Code01_AStarAlgorithm
 * @date 2023年11月27日 17:24
 */
// A*算法模版（对数器验证）
public class Code01_AStarAlgorithm {
    public static int[] move = new int[]{-1, 0, 1, 0, -1};

    public static int minDistance1(int[][] grid, int startX, int startY, int targetX, int targetY) {
        if (grid[startX][startY] == 0 || grid[targetX][targetY] == 0) {
            return -1;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] distance = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        distance[startX][startY] = 1;
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        heap.add(new int[]{startX, startY, 1});
        while (!heap.isEmpty()) {
            int[] poll = heap.poll();
            int x = poll[0];
            int y = poll[1];
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            if (x == targetX && y == targetY) {
                return distance[x][y];
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i];
                int ny = y + move[i + 1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && grid[nx][ny] == 1 && distance[x][y] + 1 < distance[nx][ny]) {
                    distance[nx][ny] = distance[x][y] + 1;
                    heap.add(new int[]{nx, ny, distance[nx][ny]});
                }
            }
        }
        return -1;
    }

    public static int minDistance2(int[][] grid, int startX, int startY, int targetX, int targetY) {
        if (grid[startX][startY] == 0 || grid[targetX][targetY] == 0) {
            return -1;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] distance = new int[n][m];
        for (int i = 0; i < n; i++)
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        distance[startX][startY] = 1;
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        heap.add(new int[]{startX, startY, 1 + f1(startX, startY, targetX, targetY)});
        while (!heap.isEmpty()) {
            int[] poll = heap.poll();
            int x = poll[0];
            int y = poll[1];
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            if (x == targetX && y == targetY) {
                return distance[x][y];
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i];
                int ny = y + move[i + 1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 1 && !visited[nx][ny] && distance[x][y] + 1 < distance[nx][ny]) {
                    distance[nx][ny] = distance[x][y] + 1;
                    heap.add(new int[]{nx, ny, distance[nx][ny] + f1(nx, ny, targetX, targetY)});
                }
            }
        }
        return -1;
    }

    // 曼哈顿距离
    public static int f1(int x, int y, int targetX, int targetY) {
        return (Math.abs(targetX - x) + Math.abs(targetY - y));
    }

    // 对角线距离
    public static int f2(int x, int y, int targetX, int targetY) {
        return Math.max(Math.abs(targetX - x), Math.abs(targetY - y));
    }

    // 欧式距离
    public static double f3(int x, int y, int targetX, int targetY) {
        return Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
    }
}
