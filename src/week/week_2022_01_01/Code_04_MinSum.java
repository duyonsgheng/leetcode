package week.week_2022_01_01;

import java.util.PriorityQueue;

/**
 * @ClassName Code_04_MinSum
 * @Author Duys
 * @Description
 * @Date 2022/4/12 17:31
 **/

// 给定一个二维数组，其中全是非负数
// 每一步都可以往上、下、左、右四个方向运动
// 走过的路径，会沿途累加数字
// 返回从左下角走到右下角的累加和最小的多少
public class Code_04_MinSum {

    public static int minSum(int[][] arr) {
        // 使用堆
        int n = arr.length;
        int m = arr[0].length;
        //  一个数组，a[0] 去到下一个点的距离，a[1] a[2] 表示 下一个点的信息
        // 距离越小的拍越前面
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[][] visit = new boolean[n][m];
        heap.add(new int[]{arr[0][0], 0, 0});
        int ans = 0;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int dis = cur[0];
            int row = cur[1];
            int col = cur[2];
            if (visit[row][col]) {
                continue;
            }
            visit[row][col] = true;
            if (row == n - 1 && col == m - 1) {
                ans = dis;
                break;
            }
            //四个方向走去
            add(arr, row + 1, col, dis, visit, heap);
            add(arr, row - 1, col, dis, visit, heap);
            add(arr, row, col + 1, dis, visit, heap);
            add(arr, row, col - 1, dis, visit, heap);
        }
        return ans;
    }

    public static void add(int[][] arr, int row, int col, int pre, boolean[][] visit, PriorityQueue<int[]> heap) {
        if (row >= 0 && row < arr.length && col >= 0 && col < arr[0].length && !visit[row][col]) {
            heap.add(new int[]{pre + arr[row][col], row, col});
        }
    }
}
