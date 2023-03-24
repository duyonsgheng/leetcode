package custom.code_2023_02;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1519
 * @Author Duys
 * @Description
 * @Date 2023/2/6 10:33
 **/
// 1519. 子树中标签相同的节点数
public class LeetCode_1519 {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int len = edges.length;
        // 把边建出来
        for (int i = 0; i < len; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        int[] ans = new int[n];
        boolean[] visited = new boolean[n];
        process(ans, 0, graph, labels.toCharArray(), visited);
        return ans;
    }

    public int[] process(int[] ans, int i, List<List<Integer>> graph, char[] arr, boolean[] visited) {
        visited[i] = true;
        // 当前节点为头的。所有的字符统计
        int[] cnt = new int[26];
        cnt[arr[i] - 'a']++;
        List<Integer> list = graph.get(i);
        for (int next : list) {
            if (visited[next]) {
                continue;
            }
            // 字节点统计
            int[] nextCnt = process(ans, next, graph, arr, visited);
            for (int n = 0; n < 26; n++)
                cnt[n] += nextCnt[n];
        }
        ans[i] += cnt[arr[i] - 'a'];
        return cnt;
    }
}
