package custom.code_2023_06;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1926
 * @date 2023年07月04日
 */
// 1926. 迷宫中离入口最近的出口
public class LeetCode_1926 {
    // 记录所有的出口，然后算一个最近距离-曼哈顿
    static int[][] dist = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static int nearestExit(char[][] maze, int[] entrance) {
        //List<int[]> entran = new ArrayList<>();
        int n = maze.length;
        int m = maze[0].length;
        boolean[][] visited = new boolean[n][m];
        int ans = Integer.MAX_VALUE;
        LinkedList<int[]> queue = new LinkedList<>();
        // 开始位置
        queue.add(new int[]{entrance[0], entrance[1], 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            visited[cur[0]][cur[1]] = true;
            for (int i = 0; i < 4; i++) {
                int[] next = dist[i];
                int nr = cur[0] + next[0];
                int nc = cur[1] + next[1];
                int di = cur[2];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }
                // 上下边 或者 左右边
                if ((nr == 0 || nr == n - 1) || (nc == 0 || nc == m - 1)) {
                    // 出口
                    if (maze[nr][nc] == '.' && !visited[nr][nc]) {
                        ans = Math.min(ans, di + 1);
                    }
                }
                if (maze[nr][nc] == '+') {
                    continue;
                }
                queue.add(new int[]{nr, nc, di + 1});
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        //maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
        System.out.println(nearestExit(new char[][]{{'+', '+', '.', '+'}, {'.', '.', '.', '+'}, {'+', '+', '+', '+'}}, new int[]{1, 2}));
        // [[".","+","."]]
        //[0,2]
        System.out.println(nearestExit(new char[][]{{'.', '+', '.'}}, new int[]{0, 2}));
    }
}
