package duys_code.day_41;

import java.util.PriorityQueue;

/**
 * @ClassName Code_03_MinGoToAim
 * @Author Duys
 * @Description
 * @Date 2021/12/29 17:03
 **/
public class Code_03_MinGoToAim {
    // 来自网易互娱
    // N个结点之间，表世界存在双向通行的道路，里世界存在双向通行的传送门.
    // 若走表世界的道路，花费一分钟.
    // 若走里世界的传送门，不花费时间，但是接下来一分钟不能走传送门.
    // 输入： T为测试用例的组数，对于每组数据:
    // 第一行：N M1 M2 N代表结点的个数1到N
    // 接下来M1行 每行两个数，u和v，表示表世界u和v之间存在道路
    // 接下来M2行 每行两个数，u和v，表示里世界u和v之间存在传送门
    // 现在处于1号结点，最终要到达N号结点，求最小的到达时间 保证所有输入均有效，不存在环等情况

    // n表示有0到n-1号城
    // roads[i] 表示i能通过走路到达的城市
    // gates[i] 表示i能通过传送到达的城市
    // 使用小根堆
    public static int fast(int n, int[][] roads, int[][] gates) {
        // dist[0][i] 前一个城市到达i，是走路的方式, 最小代价，distance[0][i]
        // dist[1][i] 前一个城市到达i，是传送的方式, 最小代价，distance[1][i]
        int[][] dist = new int[2][n];
        for (int i = 0; i < n; i++) {
            dist[0][i] = Integer.MAX_VALUE;
            dist[1][i] = Integer.MAX_VALUE;
        }
        // 根据代价排序，最小的排上面
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        queue.add(new Node(0, 0, 0));
        boolean[][] vis = new boolean[2][n];
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (vis[cur.pre][cur.city]) {
                continue;
            }
            vis[cur.pre][cur.city] = true;
            // 走路
            for (int next : roads[cur.city]) {
                if (dist[0][next] > cur.cost + 1) {
                    dist[0][next] = cur.cost + 1;
                    queue.add(new Node(0, next, dist[0][next]));
                }
            }
            // 如果之前是走路来的，当前能传送
            if (cur.pre == 0) {
                for (int next : roads[cur.city]) {
                    if (dist[1][next] > cur.cost) {
                        dist[1][next] = cur.cost;
                        queue.add(new Node(1, next, dist[1][next]));
                    }
                }
            }
        }
        return Math.min(dist[0][n - 1], dist[1][n - 1]);
    }

    public static class Node {
        public int pre; // 通过什么样的方式来到 当前城市的 0是走路，1是传送
        public int city;
        public int cost;

        public Node(int p, int c, int o) {
            pre = p;
            city = c;
            cost = o;
        }
    }
}
