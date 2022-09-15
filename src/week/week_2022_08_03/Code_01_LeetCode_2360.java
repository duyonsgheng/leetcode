package week.week_2022_08_03;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_01_LeetCode_2360
 * @Author Duys
 * @Description
 * @Date 2022/8/18 13:40
 **/
// 2360. 图中的最长环
public class Code_01_LeetCode_2360 {

    public int longestCycle2(int[] edges) {
        int n = edges.length;
        int[] ids = new int[n];
        int ans = -1;
        // 类似下标循环怼
        for (int from = 0, cnt = 1; from < n; from++) {
            if (ids[from] != 0) {
                continue;
            }
            // 从当前点出发
            for (int cur = from, fromId = cnt; cur != -1; cur = edges[cur]) {
                if (ids[cur] > 0) {
                    if (ids[cur] >= fromId) {
                        ans = Math.max(ans, cnt - ids[cur]);
                    }
                    break;
                }
                ids[cur] = cnt++;
            }
        }
        return ans;
    }


    // 1.强联通分量的做法
    public static int longestCycle(int[] edges) {
        // 生成图
        List<List<Integer>> graph = new ArrayList<>();
        int n = edges.length;
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            if (edges[i] != -1) {
                graph.get(i).add(edges[i]);
            }
        }
        StronglyConnectedComponents connectedComponents = new StronglyConnectedComponents(graph);
        int[] scc = connectedComponents.getScc();
        // 集团数
        int[] count = new int[connectedComponents.getSccn() + 1];
        for (int i = 0; i < n; i++) {
            count[scc[i]]++;
        }
        int ans = -1;
        for (int c : count) {
            ans = Math.max(ans, c > 1 ? 1 : -1);
        }
        return ans;
    }

    public static class StronglyConnectedComponents {
        public List<List<Integer>> nexts;
        public int[] stack;
        public int stackSize;
        public int n;
        public int cnt;
        public int[] dfn;
        public int[] low;
        public int[] scc;
        public int sccn;

        public StronglyConnectedComponents(List<List<Integer>> edges) {
            nexts = edges;
            init();
            build();
        }

        public int[] getScc() {
            return scc;
        }

        public int getSccn() {
            return sccn;
        }

        // 有向无环图
        public List<List<Integer>> getGraph() {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= sccn; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < n; i++) {
                for (int j : nexts.get(i)) {
                    if (scc[i] != scc[j]) {
                        graph.get(scc[i]).add(scc[j]);
                    }
                }
            }
            return graph;
        }

        private void init() {
            n = nexts.size();
            stack = new int[n];
            dfn = new int[n];
            low = new int[n];
            scc = new int[n];
        }

        private void build() {
            for (int i = 0; i < n; i++) {
                if (dfn[i] == 0) {
                    tarjan(i);
                }
            }
        }

        private void tarjan(int from) {
            dfn[from] = low[from] = ++cnt;
            stack[stackSize++] = from;
            for (int next : nexts.get(from)) {
                if (dfn[next] == 0) {
                    tarjan(next);
                }
                // 抓有效遍历的，最小的顶点编号
                if (scc[next] == 0) {
                    low[from] = Math.min(low[next], low[from]);
                }
            }
            // 当前节点遍历结束，搞合并
            if (dfn[from] == low[from]) {
                sccn++;
                int top = -1;
                do {
                    top = stack[--stackSize];
                    scc[top] = sccn;
                } while (top != from);
            }
        }
    }
}
