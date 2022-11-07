package custom.code_2022_11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName LeetCode_1042
 * @Author Duys
 * @Description
 * @Date 2022/11/7 16:46
 **/
// 1042. 不邻接植花
public class LeetCode_1042 {

    public int[] gardenNoAdj(int n, int[][] paths) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] path : paths) {
            graph.get(path[0]).add(path[1]);
            graph.get(path[1]).add(path[0]);
        }
        boolean[] visited = new boolean[n];
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                process(ans, visited, i, graph);
            }
        }
        return ans;
    }

    public static void process(int[] ans, boolean[] visited, int index, List<List<Integer>> graph) {
        visited[index] = true;
        int c = 0;
        for (int i = 0; i < 4; i++) {
            // 可以用的颜色
            if ((ans[index] & (1 << i)) == 0) {
                c = i + 1;
                break;
            }
        }
        ans[index] = c;
        List<Integer> nexts = graph.get(index + 1);
        if (nexts == null || nexts.size() == 0) {
            return;
        }
        for (int next : nexts) {
            // 如果我前一个位置还没设置
            if (!visited[next - 1]) {
                ans[next - 1] |= 1 << (c - 1);
            }
        }
        for (int next : nexts) {
            // 如果我前一个位置还没设置
            if (!visited[next - 1]) {
                process(ans, visited, next - 1, graph);
            }
        }
    }

    class Solution {
        public int[] gardenNoAdj(int n, int[][] paths) {
            int[] colors = new int[]{1, 2, 3, 4};
            int[] ans = new int[n];
            List<Integer>[] edges = new List[n];
            Set<Integer>[] useds = new Set[n];
            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<>();
                useds[i] = new HashSet<>();
            }
            for (int[] edge : paths) {
                edges[edge[0] - 1].add(edge[1] - 1);
                edges[edge[1] - 1].add(edge[0] - 1);
            }
            for (int i = 0; i < n; i++) {
                for (int color : colors) {
                    if (!useds[i].contains(color)) {
                        ans[i] = color;
                        for (int next : edges[i]) {
                            if (next > i)
                                useds[next].add(color);
                        }
                        break;
                    }
                }
            }
            return ans;
        }
    }
}
