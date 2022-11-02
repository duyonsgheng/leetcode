package custom.code_2022_11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LeetCode_994
 * @Author Duys
 * @Description
 * @Date 2022/11/2 10:18
 **/
// 994. 腐烂的橘子
public class LeetCode_994 {
    // 一次一层
    // bfs
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int goods = 0; // 好的橘子
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    goods++;
                }
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        int ans = 0;
        while (goods > 0 && !queue.isEmpty()) {
            // 一次一层往外感染吧
            ans++;
            int curSize = queue.size();
            // 一次一层
            for (int i = 0; i < curSize; i++) {
                int[] cur = queue.poll();
                int row = cur[0];
                int col = cur[1];
                // 上下左右走去
                if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                    goods--;
                    grid[row - 1][col] = 2;
                    queue.add(new int[]{row - 1, col});
                }

                if (row + 1 < n && grid[row + 1][col] == 1) {
                    goods--;
                    grid[row + 1][col] = 2;
                    queue.add(new int[]{row + 1, col});
                }

                if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                    goods--;
                    grid[row][col - 1] = 2;
                    queue.add(new int[]{row, col - 1});
                }

                if (col + 1 < m && grid[row][col + 1] == 1) {
                    goods--;
                    grid[row][col + 1] = 2;
                    queue.add(new int[]{row, col + 1});
                }
            }
        }
        if (goods > 0) {
            return -1;
        }
        return ans;
    }
}
