package custom.code_2022_11;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ClassName LeetCode_1162
 * @Author Duys
 * @Description
 * @Date 2022/11/18 15:04
 **/
// 1162. 地图分析
public class LeetCode_1162 {
    // 我就枚举所有的陆地单元格，然后一圈一圈的遍历
    public int maxDistance(int[][] grid) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int n = grid.length;
        // 距离最大
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]); // 所有的陆地加入
        int[][] cnt = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(cnt[i], Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    cnt[i][j] = 0;
                    queue.add(new int[]{i, j, 0});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dirs[i][0];
                int ny = cur[1] + dirs[i][1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                // 如果从当前位置过去的是小于原始距离的，可以尝试去
                if (cur[2] + 1 < cnt[nx][ny]) {
                    cnt[nx][ny] = cur[2] + 1;
                    queue.add(new int[]{nx, ny, cnt[nx][ny]});
                }
            }
        }
        int ans = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, cnt[i][j]);
                }
            }
        }
        return ans == Integer.MIN_VALUE ? -1 : ans;
    }
}
