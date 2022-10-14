package week.week_2022_10_02;

import java.util.PriorityQueue;

/**
 * @ClassName Code_04_LeetCode_864
 * @Author Duys
 * @Description
 * @Date 2022/10/13 13:32
 **/
//https://leetcode.cn/problems/shortest-path-to-get-all-keys/
// 864
public class Code_04_LeetCode_864 {
    // 对 dijkstra 的改写。添加点
    // 添加一位表示获取的钥匙情况
    public int shortestPathAllKeys(String[] grid) {
        int n = grid.length;
        char[][] map = new char[n][];
        for (int i = 0; i < grid.length; i++) {
            map[i] = grid[i].toCharArray();
        }
        int m = map[0].length;
        return dijkstra(map, n, m);
    }

    public static int dijkstra(char[][] map, int n, int m) {

        int sx = 0;
        int sy = 0;
        int keys = 0;
        // 获取开始位置和钥匙数量
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '@') {
                    sx = i;
                    sy = j;
                }
                if (map[i][j] >= 'a' && map[i][j] <= 'z') {
                    keys++;
                }
            }
        }
        int limit = (1 << keys) - 1;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[3] - b[3]);
        boolean[][][] visited = new boolean[n][m][limit + 1];
        heap.add(new int[]{sx, sy, 0, 0});
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int x = cur[0];
            int y = cur[1];
            int status = cur[2];
            int cost = cur[3];
            // 收集齐全了
            if (status == limit) {
                return cost;
            }
            if (visited[x][y][status]) {
                continue;
            }
            visited[x][y][status] = true;
            add(x + 1, y, status, cost, n, m, map, visited, heap);
            add(x - 1, y, status, cost, n, m, map, visited, heap);
            add(x, y + 1, status, cost, n, m, map, visited, heap);
            add(x, y - 1, status, cost, n, m, map, visited, heap);
        }
        return -1;
    }

    public static void add(int x, int y, int status, int cost, int n, int m, char[][] map, boolean[][][] visited, PriorityQueue<int[]> heap) {
        if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y][status] || map[x][y] == '#') {
            return;
        }
        // 锁 ，必须要要有对应的钥匙
        if (map[x][y] >= 'A' && map[x][y] <= 'Z') {
            // 获取到了对应的钥匙
            if (!visited[x][y][status] && (status & (1 << (map[x][y] - 'A'))) != 0) {
                heap.add(new int[]{x, y, status, cost + 1});
            }
        } else {
            // 如果当前是钥匙
            if (map[x][y] >= 'a' && map[x][y] <= 'z') {
                status |= (1 << (map[x][y] - 'a'));
            }
            if (!visited[x][y][status]) {
                heap.add(new int[]{x, y, status, cost + 1});
            }
        }
    }
}
