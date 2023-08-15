package custom.code_2023_08;

import java.util.*;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2039
 * @date 2023年08月02日
 */
// 2039. 网络空闲的时刻
// https://leetcode.cn/problems/the-time-when-the-network-becomes-idle/
public class LeetCode_2039 {
    public static int networkBecomesIdle1(int[][] edges, int[] patience) {
        int n = patience.length;
        // 计算每台服务器到主服务器的最短距离
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        int[] dist = new int[n];
        dist[0] = 0;
        // 路径小的都在前面
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            // 把最短的路径拉出来
            if (dist[cur[0]] == 0) {
                dist[cur[0]] = cur[1];
            } else
                dist[cur[0]] = Math.min(dist[cur[0]], cur[1]);
            for (int next : graph.get(cur[0])) {
                queue.add(new int[]{next, cur[1] + 1});
            }
        }
        // 0 1 2
        // 0 2 1
        //   2
        // 1 -
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int di = dist[i] * 2, t = patience[i];
            int cur = di <= t ? di : (di - 1) / t * t + di;
            if (cur > ans) ans = cur;
        }
        return ans + 1;
    }

    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<Integer>());
        }
        boolean[] visit = new boolean[n];
        for (int[] v : edges) {
            graph.get(v[0]).add(v[1]);
            graph.get(v[1]).add(v[0]);
        }

        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.offer(0);
        visit[0] = true;
        // 一次一层的往下
        int dist = 1;
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                int curr = queue.poll();
                for (int v : graph.get(curr)) {
                    if (visit[v]) {
                        continue;
                    }
                    queue.offer(v);
                    int time = patience[v] * ((2 * dist - 1) / patience[v]) + 2 * dist + 1;
                    ans = Math.max(ans, time);
                    visit[v] = true;
                }
                size--;
            }
            dist++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = networkBecomesIdle1(new int[][]{{0, 1}, {1, 2}}, new int[]{0, 2, 1});
        System.out.println(i);
    }
}
