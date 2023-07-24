package custom.code_2023_07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1976
 * @date 2023年07月19日
 */
// 1976. 到达目的地的方案数
// https://leetcode.cn/problems/number-of-ways-to-arrive-at-destination/
public class LeetCode_1976 {
    // 普通的dj + dp
    public int countPaths(int n, int[][] roads) {
        // 建图
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // 双边的
        for (int[] road : roads) {
            graph.get(road[0]).add(new int[]{road[1], road[2]});
            graph.get(road[1]).add(new int[]{road[0], road[2]});
        }
        int mod = 1_000_000_007;
        int[] cnt = new int[n]; // 记录这一条链路是否存在更小的或者相等的，存在更小的就更新，否则相等就相加然后取模
        int[] dist = new int[n]; // 从起点到当前总共的时间
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 根据路径排序
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        dist[0] = 1;
        cnt[0] = 1;
        // 开始自己到自己
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int from = cur[0];
            int dis = cur[1];
            if (dis > dist[from]) {
                continue;
            }
            // 下级
            for (int[] arr : graph.get(from)) {
                int next = arr[0];
                int ndis = arr[1] + dist[from];
                if (ndis < dist[next]) {
                    // 有短的路径产生，那么集成之前的
                    cnt[next] = cnt[from];
                    dist[next] = ndis;
                    queue.add(new int[]{next, ndis});
                } else if (ndis == dist[next]) {
                    cnt[next] = (cnt[next] + cnt[from]) % mod;
                }
            }
        }
        return cnt[n - 1];
    }
}
