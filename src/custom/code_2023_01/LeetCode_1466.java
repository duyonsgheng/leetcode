package custom.code_2023_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_1466
 * @Author Duys
 * @Description
 * @Date 2023/1/31 14:42
 **/
// 1466. 重新规划路线
public class LeetCode_1466 {

    public int minReorder(int n, int[][] connections) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] levels = new int[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] con : connections) {
            graph.get(con[0]).add(con[1]);
            graph.get(con[1]).add(con[0]);
        }
        Arrays.fill(levels, -1);
        dfs(graph, levels, 0, 0);
        int ans = 0;
        for (int[] con : connections) {
            // 如果 a到b有路，但是a还是b的下一层，需要反转路线
            if (levels[con[0]] < levels[con[1]]) {
                ans++;
            }
        }
        return ans;
    }

    public void dfs(List<List<Integer>> graph, int[] levels, int start, int level) {
        levels[start] = level;// 当前城市是第几层
        List<Integer> nexts = graph.get(start);
        for (int next : nexts) {
            if (levels[next] < 0) {
                dfs(graph, levels, next, level + 1);
            }
        }
    }
}
