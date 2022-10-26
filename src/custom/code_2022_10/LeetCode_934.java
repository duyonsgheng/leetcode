package custom.code_2022_10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LeetCode_934
 * @Author Duys
 * @Description
 * @Date 2022/10/25 9:52
 **/
public class LeetCode_934 {
    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    Queue<int[]> queue = new LinkedList<>();
                    dfs(i, j, grid, queue);
                    int ans = 0;
                    while (!queue.isEmpty()) {
                        int size = queue.size();// 当前所有的全部在队列中了，是一个岛屿的
                        for (int k = 0; k < size; k++) {
                            int[] cur = queue.poll();
                            int x = cur[0];
                            int y = cur[1];
                            // 上下左右走
                            for (int m = 0; m < 4; m++) {
                                int nextX = x + dirs[m][0];
                                int nextY = y + dirs[m][1];
                                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
                                    continue;
                                }
                                // 需要走一步
                                if (grid[nextX][nextY] == 0) {
                                    queue.offer(new int[]{nextX, nextY});
                                    grid[nextX][nextY] = -1;
                                } else if (grid[nextX][nextY] == 1) {
                                    return ans;
                                }
                            }
                        }
                        ans++;
                    }
                }
            }
        }
        return 0;
    }

    // 把其中的一座岛，搞定
    public void dfs(int x, int y, int[][] arr, Queue<int[]> queue) {
        if (x < 0 || y < 0 || x >= arr.length || y >= arr[0].length || arr[x][y] != 1) {
            return;
        }
        queue.offer(new int[]{x, y});
        arr[x][y] = -1;
        dfs(x - 1, y, arr, queue);
        dfs(x + 1, y, arr, queue);
        dfs(x, y - 1, arr, queue);
        dfs(x, y + 1, arr, queue);
    }
}
