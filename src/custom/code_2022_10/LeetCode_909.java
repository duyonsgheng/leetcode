package custom.code_2022_10;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ClassName LeetCode_909
 * @Author Duys
 * @Description
 * @Date 2022/10/17 10:12
 **/
//
public class LeetCode_909 {
    // dijkstra
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] vis = new boolean[n * n + 1];
        //这里使用的是每个格子的编号，我们也可以使用的是行列，当奇数行从左往右，偶数行从右往左。当前编号17，根据有多少行多少列，可以算出所在的行列、。然后跑dijkstra
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int i = 1; i <= 6; ++i) {
                int nxt = p[0] + i;
                if (nxt > n * n) { // 超出边界
                    break;
                }
                int[] rc = id2rc(nxt, n); // 得到下一步的行列
                if (board[rc[0]][rc[1]] > 0) { // 存在蛇或梯子
                    nxt = board[rc[0]][rc[1]];
                }
                if (nxt == n * n) { // 到达终点
                    return p[1] + 1;
                }
                if (!vis[nxt]) {
                    vis[nxt] = true;
                    queue.offer(new int[]{nxt, p[1] + 1}); // 扩展新状态
                }
            }
        }
        return -1;
    }


    public int[] id2rc(int id, int n) {
        int r = (id - 1) / n, c = (id - 1) % n;
        if (r % 2 == 1) { // 因为行是存在交替的。所以分奇偶，奇数行从右往左，偶数行从左往右
            c = n - 1 - c;
        }
        return new int[]{n - 1 - r, c};
    }
}
