package custom.code_2023_08;

import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2087
 * @date 2023年08月14日
 */
// 2087. 网格图中机器人回家的最小代价
// https://leetcode.cn/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/
public class LeetCode_2087 {
    public int[][] dist = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // 迪杰斯特拉，会超时
    public int minCost1(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int n = rowCosts.length;
        int m = colCosts.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.add(new int[]{startPos[0], startPos[1], 0});
        boolean[][] visited = new boolean[n][m];
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (poll[0] == homePos[0] && poll[1] == homePos[1]) {
                return poll[2];
            }
            if (visited[poll[0]][poll[1]]) {
                continue;
            }
            visited[poll[0]][poll[1]] = true;
            // 上下左右走去
            for (int i = 0; i < 4; i++) {
                int nr = poll[0] + dist[i][0];
                int nc = poll[1] + dist[i][1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc]) {
                    if (nr == poll[0]) { // 行相等，左右走
                        queue.add(new int[]{nr, nc, colCosts[nc] + poll[2]});
                    } else { // 列相等，上下走
                        queue.add(new int[]{nr, nc, rowCosts[nr] + poll[2]});
                    }
                }
            }
        }
        return -1;
    }

    // 贪心，横着走，和竖着走
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int dx = startPos[0] - homePos[0]; // 横向距离
        int dy = startPos[1] - homePos[1]; // 纵向距离
        int ans = 0;

        // 横向距离代价
        if (dx < 0) {
            for (int i = startPos[0] + 1; i <= homePos[0]; i++) {
                ans += rowCosts[i];
            }
        } else {
            for (int i = startPos[0] - 1; i >= homePos[0]; i--) {
                ans += rowCosts[i];
            }
        }
        // 纵向距离
        if (dy < 0) {
            for (int i = startPos[1] + 1; i <= homePos[1]; i++) {
                ans += colCosts[i];
            }
        } else {
            for (int i = startPos[1] - 1; i >= homePos[1]; i--) {
                ans += colCosts[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCode_2087 lc = new LeetCode_2087();
        System.out.println(lc.minCost(new int[]{1, 0}, new int[]{2, 3}, new int[]{5, 4, 3}, new int[]{8, 2, 6, 7}));
    }
}
