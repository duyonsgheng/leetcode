package custom.code_2023_04;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1631
 * @Author Duys
 * @Description
 * @Date 2023/4/21 10:07
 **/
// 1631. 最小体力消耗路径
public class LeetCode_1631 {
    static int[][] step = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        // 绝对值差值最小的
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.add(new int[]{0, 0, 0});
        boolean[][] visited = new boolean[n][m];
        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            visited[cur[0]][cur[1]] = true;
            if (cur[0] == n - 1 && cur[1] == m - 1) {
                ans = Math.min(ans, cur[2]);
            }
            // 上下左右
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + step[i][0];
                int ny = cur[1] + step[i][1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                    int diff = Math.abs(heights[nx][ny] - heights[cur[0]][cur[1]]);
                    queue.add(new int[]{nx, ny, Math.max(diff, cur[2])});
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // heights = [[1,2,2],[3,8,2],[5,3,5]]
        //System.out.println(minimumEffortPath(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}}));
        // heights = [[1,2,3],[3,8,4],[5,3,5]]
        //System.out.println(minimumEffortPath(new int[][]{{1, 2, 3}, {3, 8, 4}, {5, 3, 5}}));
        System.out.println(minimumEffortPath(new int[][]{{10, 8}, {10, 8}, {1, 2}, {10, 3}, {1, 3}, {6, 3}, {5, 2}}));
        // [[10,8],
        // [10,8],
        // [1,2],
        //[10,3],
        // [1,3],
        // [6,3],
        // [5,2]]
    }


}
