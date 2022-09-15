package custom.code_2022_08;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LeetCode_542
 * @Author Duys
 * @Description
 * @Date 2022/8/23 15:39
 **/
// 0 1 矩阵
public class LeetCode_542 {
    //1.dp的解法 上 下 左 右 四个方向
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // dp表示 i j 位置到最近的0 的距离
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], (m * n) + 1);
        }
        // 如果值为0，那么dp就是0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                }
            }
        }
        // 左上
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 向左移动
                if (j >= 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
                // 向上
                if (i >= 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
            }
        }
        // 左下
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                // 向左移动
                if (j >= 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
                // 向上
                if (i <= m - 2) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                }
            }
        }
        // 右上
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (i >= 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
                if (j <= n - 2) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                }
            }
        }
        // 右下
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i <= n - 2) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                }
                if (j <= n - 2) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                }
            }
        }
        return dp;
    }

    // 最短路径的算法
    public int[][] updateMatrix2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] arr = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // 入队列
                    queue.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            add(m, n, x + 1, y, dist, queue, visited, arr);
            add(m, n, x - 1, y, dist, queue, visited, arr);
            add(m, n, x, y + 1, dist, queue, visited, arr);
            add(m, n, x, y - 1, dist, queue, visited, arr);
        }
        return arr;
    }

    public void add(int m, int n, int x, int y, int pre, Queue<int[]> queue, boolean[][] visited, int[][] arr) {
        if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
            arr[x][y] = pre + 1;
            queue.add(new int[]{x, y, pre + 1});
            visited[x][y] = true;
        }
    }
}
