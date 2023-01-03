package custom.code_2022_12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LeetCode_1391
 * @Author Duys
 * @Description
 * @Date 2022/12/26 17:35
 **/
// 1391. 检查网格中是否存在有效路径
public class LeetCode_1391 {

    //1 表示连接左单元格和右单元格的街道。
    //2 表示连接上单元格和下单元格的街道。
    //3表示连接左单元格和下单元格的街道。
    //4 表示连接右单元格和下单元格的街道。
    //5 表示连接左单元格和上单元格的街道。
    //6 表示连接右单元格和上单元格的街道。
    // [[2,4,3],
    // [6,5,2]]
    public static boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 记录当前的位置，以及从哪个方向来的
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        queue.add(new int[]{0, 0, grid[0][0]});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            if (x == m - 1 && y == n - 1) {
                return true;
            }
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            add(cur[2], x, y, m, n, grid, queue);
        }
        return false;
    }

    public static boolean checkLetf(int x, int y, int[][] grid) {
        return grid[x][y] != 2 && grid[x][y] != 3 && grid[x][y] != 5;
    }

    public static boolean checkUp(int x, int y, int[][] grid) {
        return grid[x][y] != 1 && grid[x][y] != 5 && grid[x][y] != 6;
    }

    public static boolean checkDown(int x, int y, int[][] grid) {
        return grid[x][y] != 1 && grid[x][y] != 3 && grid[x][y] != 4;
    }

    public static boolean checkRight(int x, int y, int[][] grid) {
        return grid[x][y] != 2 && grid[x][y] != 4 && grid[x][y] != 6;
    }

    public static void add(int xur, int x, int y, int m, int n, int[][] grid, Queue<int[]> queue) {
        if (xur == 1) {
            if (y + 1 < n && checkRight(x, y + 1, grid)) {
                queue.add(new int[]{x, y + 1, grid[x][y + 1]});
            }
            if (y - 1 >= 0 && checkLetf(x, y - 1, grid)) {
                queue.add(new int[]{x, y - 1, grid[x][y - 1]});
            }
        } else if (xur == 2) {
            if (x + 1 < m && checkDown(x + 1, y, grid)) {
                queue.add(new int[]{x + 1, y, grid[x + 1][y]});
            }
            if (x - 1 >= 0 && checkUp(x - 1, y, grid)) {
                queue.add(new int[]{x - 1, y, grid[x - 1][y]});
            }
        } else if (xur == 3) {
            if (x + 1 < m && checkDown(x + 1, y, grid)) {
                queue.add(new int[]{x + 1, y, grid[x + 1][y]});
            }
            if (y - 1 >= 0 && checkLetf(x, y - 1, grid)) {
                queue.add(new int[]{x, y - 1, grid[x][y - 1]});
            }
        } else if (xur == 4) {
            if (x + 1 < m && checkDown(x + 1, y, grid)) {
                queue.add(new int[]{x + 1, y, grid[x + 1][y]});
            }
            if (y + 1 < n && checkRight(x, y + 1, grid)) {
                queue.add(new int[]{x, y + 1, grid[x][y + 1]});
            }
        } else if (xur == 5) {
            if (x - 1 >= 0 && checkUp(x - 1, y, grid)) {
                queue.add(new int[]{x - 1, y, grid[x - 1][y]});
            }
            if (y - 1 >= 0 && checkLetf(x, y - 1, grid)) {
                queue.add(new int[]{x, y - 1, grid[x][y - 1]});
            }
        } else if (xur == 6) {
            if (y + 1 < n && checkRight(x, y + 1, grid)) {
                queue.add(new int[]{x, y + 1, grid[x][y + 1]});
            }
            if (x - 1 >= 0 && checkUp(x - 1, y, grid)) {
                queue.add(new int[]{x - 1, y, grid[x - 1][y]});
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{2, 4, 3}, {6, 5, 2}};
        System.out.println(hasValidPath(arr));
    }
}
