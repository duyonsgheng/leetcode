package week.week_2021_12_01;

import java.util.PriorityQueue;

/**
 * @ClassName Code_01_X_to_Y_MinDist
 * @Author Duys
 * @Description
 * @Date 2022/2/11 17:43
 **/
public class Code_01_X_to_Y_MinDist {
    /**
     * int n, int[][] roads, int x, int y
     * n表示城市数量，城市编号0~n-1
     * roads[i][j] == distance，表示城市i到城市j距离为distance(无向图)
     * 求城市x到城市y的最短距离
     */

    // 直接堆搞定
    public static int minDistance(int n, int[][] roads, int x, int y) {
        // 搞一个路
        int[][] map = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                map[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int[] road : roads) {
            map[road[0]][road[1]] = Math.min(map[road[0]][road[1]], road[2]);
            map[road[1]][road[0]] = Math.min(map[road[1]][road[0]], road[2]);
        }
        // 从源点出发到这个点
        boolean[] visi = new boolean[n + 1];
        // int[2] 从远点来到当前点的距离 和 当前点
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.add(new int[]{0, x});
        int ans = Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int dis = cur[0];
            int point = cur[1];
            if (visi[point]) {
                continue;
            }
            visi[point] = true;
            if (point == y) {
                return dis;
            }
            for (int next = 1; next <= n; next++) {
                // 接着走
                if (next != point && map[point][next] != Integer.MAX_VALUE && !visi[next]) {
                    heap.add(new int[]{dis + map[point][next], next});
                }
            }
        }
        return ans;
    }
}
