package custom.code_2023_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1786
 * @Author Duys
 * @Description
 * @Date 2023/5/22 10:06
 **/
// 1786. 从第一个节点出发到最后一个节点的受限路径数
public class LeetCode_1786 {
    static int mod = 1_000_000_007;

    public static int countRestrictedPaths(int n, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        // 图建好
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        // 编号和距离
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[]{n, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (visited[cur[0]]) {
                continue;
            }
            visited[cur[0]] = true;
            for (int[] next : graph.get(cur[0])) {
                dist[next[0]] = Math.min(dist[next[0]], dist[cur[0]] + next[1]);
                queue.add(new int[]{next[0], dist[next[0]]});
            }
        }
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{i + 1, dist[i + 1]};
        }
        // 根据距离排序
        Arrays.sort(arr, (a, b) -> a[1] - b[1]);
        // 从f[n]到f[1]算
        int[] f = new int[n + 1];
        f[n] = 1;
        for (int i = 0; i < n; i++) {
            int x = arr[i][0], cost = arr[i][1];
            for (int[] next : graph.get(x)) {
                if (cost > dist[next[0]]) {
                    f[x] += f[next[0]];
                    f[x] %= mod;
                }
            }
        }
        return f[1];
    }

    public static void main(String[] args) {
        // n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
        System.out.println(countRestrictedPaths(5, new int[][]{{1, 2, 3}, {1, 3, 3}, {2, 3, 1}, {1, 4, 2}, {5, 2, 2}, {3, 5, 1}, {5, 4, 10}}));
        // n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]

    }
}
