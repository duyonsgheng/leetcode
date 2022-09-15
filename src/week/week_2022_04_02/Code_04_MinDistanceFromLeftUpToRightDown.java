package week.week_2022_04_02;

import java.util.PriorityQueue;

/**
 * @ClassName Code_04_MinDistanceFromLeftUpToRightDown
 * @Author Duys
 * @Description
 * @Date 2022/4/13 14:29
 **/
// 来自网易
// 3.27笔试
// 一个二维矩阵，上面只有 0 和 1，只能上下左右移动
// 如果移动前后的元素值相同，则耗费 1 ，否则耗费 2。
// 问从左上到右下的最小耗费
public class Code_04_MinDistanceFromLeftUpToRightDown {

    // 二话不说，堆
    public static int minCost(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[][] visit = new boolean[n][m];
        heap.add(new int[]{0, 0, 0});
        int ans = 0;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int cost = cur[0];
            int row = cur[1];
            int col = cur[2];
            if (visit[row][col]) {
                continue;
            }
            visit[row][col] = true;
            if (row == n - 1 && col == m - 1) {
                ans = cost;
                break;
            }
            add(arr, cost, arr[row][col], row + 1, col, visit, heap);
            add(arr, cost, arr[row][col], row - 1, col, visit, heap);
            add(arr, cost, arr[row][col], row, col + 1, visit, heap);
            add(arr, cost, arr[row][col], row, col - 1, visit, heap);
        }
        return ans;
    }

    public static void add(int[][] arr, int preCost, int pre, int row, int col, boolean[][] vi, PriorityQueue<int[]> heap) {
        if (row >= 0 && row < arr.length && col >= 0 && col < arr[0].length && !vi[row][col]) {
            heap.add(new int[]{preCost + arr[row][col] == pre ? 1 : 2, row, col});
        }
    }
}
