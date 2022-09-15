package week.week_2022_05_02;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @ClassName Code_04_OneEdgeMagicMinPathSum
 * @Author Duys
 * @Description
 * @Date 2022/5/12 17:46
 **/

// 来自网易
// 给出一个有n个点，m条有向边的图
// 你可以施展魔法，把有向边，变成无向边
// 比如A到B的有向边，权重为7。施展魔法之后，A和B通过该边到达彼此的代价都是7。
// 求，允许施展一次魔法的情况下，1到n的最短路，如果不能到达，输出-1。
// n为点数, 每条边用(a,b,v)表示，含义是a到b的这条边，权值为v
// 点的数量 <= 10^5，边的数量 <= 2 * 10^5，1 <= 边的权值 <= 10^6
public class Code_04_OneEdgeMagicMinPathSum {

    // 这个题和之前哪个可以穿越的题很像
    // 我们把每一个位置copy一份
    // 我们把每一条边都创建一个相反的边
    // 来到当前位置的时候，之前是否用过魔法了，用过魔法了，那么当前点只能是实点，并且只能走实边


    public static int min(int n, int[][] roads) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] r : roads) {
            // 从 a点到b点 是实的
            graph.get(r[0]).add(new int[]{0, r[1], r[2]});
            // 我们建一条虚边，是从 b到a的
            graph.get(r[1]).add(new int[]{1, r[0], r[2]});
        }
        // 下面就是迪杰斯特拉
        // 根据代价排序
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[2][n + 1]; // 0表示实，1表示虚
        // 未使用魔法，来到1的代价是0
        heap.add(new int[]{0, 1, 0});
        int ans = Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            // 来到当前位置
            int[] cur = heap.poll();
            if (visited[cur[0]][cur[1]]) {// 已经来到过一次了，就不要重复算了
                continue;
            }
            visited[cur[0]][cur[1]] = true;
            if (cur[1] == n) {
                ans = Math.min(ans, cur[2]);
                // 都没来过，可以返回了
                if (visited[0][n] && visited[1][n]) {
                    break;
                }
            }

            // 否则去我得下级继续把
            for (int[] edge : graph.get(cur[1])) {
                // 之前没用过魔法
                if (cur[0] + edge[0] == 0) {
                    if (!visited[0][edge[1]]) {
                        heap.add(new int[]{0, edge[1], cur[2] + edge[2]});
                    }
                }
                // 说明之前用过魔法了，
                if (cur[0] + edge[0] == 1) {
                    if (!visited[1][edge[1]]) {
                        heap.add(new int[]{1, edge[1], cur[2] + edge[2]});
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
