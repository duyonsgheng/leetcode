package week.week_2022_02_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_01_LeetCode_787
 * @Author Duys
 * @Description
 * @Date 2022/3/29 13:12
 **/
public class Code_01_LeetCode_787 {
    // 有 n 个城市通过一些航班连接。给你一个数组flights ，其中flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
    //现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k站中转的路线，使得从 src 到 dst 的 价格最便宜 ，
    //并返回该价格。 如果不存在这样的路线，则输出 -1。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops

    // 普通的解答，宽度优先遍历
    // n - 城市数量
    // flights - 航班信息
    // src 出发点
    // dst 目标点
    // k k次中转
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        // 准备图
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] line : flights) {
            graph.get(line[0]).add(new int[]{line[1], line[2]});
        }
        // cost[i]从src开始达到i号城市最少的费用
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        // key - 城市编号
        // value - 从src开始到当前城市的最小花费
        Map<Integer, Integer> dits = new HashMap<>();
        dits.put(src, 0);
        for (int i = 0; i <= k; i++) {
            Map<Integer, Integer> nextDist = new HashMap<>();
            for (Map.Entry<Integer, Integer> cur : dits.entrySet()) {
                int from = cur.getKey();
                int preCost = cur.getValue();
                // 能去哪些城市
                for (int[] line : graph.get(from)) {
                    int to = line[0];
                    int curCost = line[1];
                    cost[to] = Math.min(cost[to], curCost + preCost);
                    nextDist.put(to, Math.min(nextDist.getOrDefault(to, Integer.MAX_VALUE), preCost + curCost));
                }
            }
            dits = nextDist;
        }
        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }

    // Bellman Ford 最短路径的算法
    public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        for (int i = 0; i <= k; i++) {
            int[] next = Arrays.copyOf(cost, n);
            for (int[] line : flights) {
                // 看看当前的起始点有没有从src来到过，如果能从src来到当前的点，那么就看看能不能推得更小
                if (cost[line[0]] != Integer.MAX_VALUE) {
                    next[line[1]] = Math.min(next[line[1]], cost[line[0]] + line[2]);
                }
            }
            cost = next;
        }
        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }
}
