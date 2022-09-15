package duys_code.day_44;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName Code_01_317_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/shortest-distance-from-all-buildings/
 * @Date 2022/1/6 13:09
 **/
public class Code_01_317_LeetCode {
    // 离建筑物最近的距离
    // 意思就是我们每一层每一层的去遍历
    public static int shortestDistance(int[][] grid) {
        int[][] dist = new int[grid.length][grid[0].length];
        int pass = 0;// 最开始我认为0 是路
        int step = Integer.MAX_VALUE;
        int[] trans = new int[]{0, 1, 0, -1, 0}; // 上下左右
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    step = bfs(grid, dist, i, j, pass--, trans);
                    if (step == Integer.MAX_VALUE) {
                        return -1;
                    }
                }
            }
        }
        return step == Integer.MAX_VALUE ? -1 : step;
    }

    // dist就是距离的压缩表，比如当第一回时，所有的点到到为1的地方去的距离是x，第二回 是y ，那么dist种记的就是x+y
    // row,col就是宽度优先遍历的出发点
    // pass 表示当前值为pass的格子才是路
    public static int bfs(int[][] grid, int[][] dist, int row, int col, int pass, int[] trans) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        int level = 0;
        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            // 一层一层的遍历，宽度优先嘛
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int[] curNode = queue.poll();
                // 从当前位置出发，上下左右走去吧
                for (int j = 1; j < trans.length; j++) {
                    int nextRow = curNode[0] + trans[i - 1];
                    int nextCol = curNode[1] + trans[i];
                    if (nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < grid[0].length && grid[nextRow][nextCol] == pass) {
                        queue.offer(new int[]{nextRow, nextCol});
                        dist[nextRow][nextCol] += level;
                        ans = Math.min(ans, dist[nextRow][nextCol]);
                        grid[nextRow][nextCol]--;
                    }
                }
            }
        }
        return ans;
    }
}
