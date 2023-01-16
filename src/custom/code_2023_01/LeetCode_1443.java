package custom.code_2023_01;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1443
 * @Author Duys
 * @Description
 * @Date 2023/1/5 16:45
 **/
// 1443. 收集树上所有苹果的最少时间
public class LeetCode_1443 {

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int ans = process(0, visited, hasApple, graph);
        return ans == -1 ? 0 : ans;
    }

    public int process(int index, boolean[] visited, List<Boolean> hasApple, List<List<Integer>> graph) {
        int cur = 0;
        for (int next : graph.get(index)) {
            if (!visited[next]) {
                visited[next] = true;
                // 以当前节点为头。发散去吧
                int nt = process(next, visited, hasApple, graph);
                if (nt != -1) {
                    // 从当前节点走过两次
                    cur += nt + 2;
                }
            }
        }
        // 如果当前有苹果，则可以返回了。
        if (hasApple.get(index)) {
            return cur;
        } else {
            // 如果没有苹果。返回 当前的cur如果是0，返回-1，否则返cur
            return cur == 0 ? -1 : cur;
        }
    }
}
