package custom.code_2022_12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1334
 * @Author Duys
 * @Description
 * @Date 2022/12/12 16:20
 **/
//1334. 阈值距离内邻居最少的城市
public class LeetCode_1334 {
    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // 每个城市的令居都来一遍
        // 如果邻居数量一样，那么就按照编号从大到小
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        int lastCity = -1;
        int lastDist = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            int dist = dijkstra(graph, i, distanceThreshold, n);
            if (lastDist > dist) {
                lastCity = i;
                lastDist = dist;
            }
        }
        return lastCity;
    }

    public static int dijkstra(List<List<int[]>> graph, int source, int limit, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        queue.add(new int[]{source, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int dis = cur[1];
            for (int[] next : graph.get(x)) {
                int nx = next[0];
                int xd = next[1];
                if (dist[nx] > dis + xd) {
                    dist[nx] = dis + xd;
                    queue.add(new int[]{nx, dist[nx]});
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (i == source) {
                continue;
            }
            if (dist[i] <= limit) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // 6
        //[[0,1,10],[0,2,1],[2,3,1],[1,3,1],[1,4,1],[4,5,10]]
        //20
        int n = 6, edges[][] = {{0, 1, 10}, {0, 2, 1}, {2, 3, 1}, {1, 4, 1}, {4, 5, 10}, {1, 3, 1}}, distanceThreshold = 20;
        System.out.println(findTheCity(n, edges, distanceThreshold));
        ;
    }
}
