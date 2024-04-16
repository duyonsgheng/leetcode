package hope.class79_treeDP2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName Code01_MinimumFuelCost
 * @date 2024年04月08日 下午 03:06
 */
// 到达首都的最少油耗
// 给你一棵 n 个节点的树（一个无向、连通、无环图）
// 每个节点表示一个城市，编号从 0 到 n - 1 ，且恰好有 n - 1 条路
// 0 是首都。给你一个二维整数数组 roads
// 其中 roads[i] = [ai, bi] ，表示城市 ai 和 bi 之间有一条 双向路
// 每个城市里有一个代表，他们都要去首都参加一个会议
// 每座城市里有一辆车。给你一个整数 seats 表示每辆车里面座位的数目
// 城市里的代表可以选择乘坐所在城市的车，或者乘坐其他城市的车
// 相邻城市之间一辆车的油耗是一升汽油
// 请你返回到达首都最少需要多少升汽油
// 测试链接 : https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital/
public class Code01_MinimumFuelCost {
    public static long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }
        int[] size = new int[n];
        long[] cost = new long[n];
        f(graph, seats, 0, -1, size, cost);
        return cost[0];
    }

    // 当前来到u，父节点是p
    public static void f(List<List<Integer>> graph, int seats, int u, int p, int[] size, long[] cost) {
        size[u] = 1;
        // 下级节点
        for (int v : graph.get(u)) {
            if (v == p) {
                continue;
            }
            f(graph, seats, v, u, size, cost);
            // 先把孩子节点的大小和代价集成了
            size[u] += size[v];
            cost[u] += cost[v];
            // 然后算我需要给我父亲返回的
            // 向上取整，比如 7个人，一辆车只能乘坐4个人，那么就需要两套
            cost[u] += (size[v] + seats - 1) / seats;
        }
    }
}
