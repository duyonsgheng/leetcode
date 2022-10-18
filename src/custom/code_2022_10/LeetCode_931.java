package custom.code_2022_10;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_931
 * @Author Duys
 * @Description
 * @Date 2022/10/18 14:46
 **/
// 931. 下降路径最小和
public class LeetCode_931 {
    // dijkstra
    public int minFallingPathSum(int[][] matrix) {
        int ans = Integer.MAX_VALUE;
        int n = matrix.length;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < n; i++) {
            heap.clear();
            heap.add(new int[]{0, i, matrix[0][i]});
            while (!heap.isEmpty()) {
                int[] poll = heap.poll();
                int x = poll[0];
                int y = poll[1];
                int cost = poll[2];
                if (x == n - 1) {
                    ans = Math.min(ans, cost);
                }
                // 往下走
                add(x + 1, y - 1, cost, n, heap, matrix);
                add(x + 1, y, cost, n, heap, matrix);
                add(x + 1, y + 1, cost, n, heap, matrix);
            }
        }
        return ans;
    }

    public void add(int x, int y, int cost, int n, PriorityQueue<int[]> heap, int[][] matrix) {
        if (x >= 0 && x < n && y >= 0 && y < n) {
            heap.add(new int[]{x, y, cost + matrix[x][y]});
        }
    }


    // 动态规划
    public int minFallingPathSum1(int[][] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 三者取其小的
                // arr[i-1][j] arr[i-1][j-1] arr[i-1][j+1]
                int cur = arr[i - 1][j];
                if (j > 0) {
                    cur = Math.min(cur, arr[i - 1][j - 1]);
                }
                if (j + 1 < n) {
                    cur = Math.min(cur, arr[i - 1][j + 1]);
                }
                arr[i][j] += cur;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int x : arr[n - 1])
            ans = Math.min(x, ans);
        return ans;
    }
}
