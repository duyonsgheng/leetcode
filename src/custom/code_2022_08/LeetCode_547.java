package custom.code_2022_08;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_547
 * @Author Duys
 * @Description
 * @Date 2022/8/23 16:21
 **/
// 547. 省份数量
public class LeetCode_547 {
    // 1.并查集
    public int findCircleNum1(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.sets;
    }

    class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int x, int y) {
            int xp = find(x);
            int yp = find(y);
            if (xp == yp) {
                return;
            }
            if (size[xp] > size[yp]) {
                size[xp] += size[yp];
                parent[yp] = xp;
            } else {
                size[yp] += size[xp];
                parent[xp] = yp;
            }
            sets--;
        }

        public boolean isSameSte(int i, int j) {
            int p1 = find(i);
            int p2 = find(j);
            return p1 == p2;
        }

        private int find(int i) {
            int index = 0;
            while (i != parent[i]) {
                help[index++] = i;
                i = parent[i];
            }
            for (index--; index >= 0; index--) {
                parent[help[index]] = i;
            }
            return i;
        }
    }

    // 2.强联通分量
    public int findCircleNum2(int[][] isConnected) {
        int n = isConnected.length;
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    edges.get(i).add(j);
                    edges.get(j).add(i);
                }
            }
        }
        StronglyConnectedComponents st = new StronglyConnectedComponents(edges);
        return st.getSccn();
    }

    class StronglyConnectedComponents {
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

        public int getSccn() {
            return sccn;
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
                    // 没跑过，就去抛一个dfs
                    tarjan(next);
                }
                // 还没属于任何一个集团
                if (scc[next] == 0) {
                    low[from] = Math.min(low[from], low[next]);
                }
            }
            // 开始合并了
            if (dfn[from] == low[from]) {
                sccn++;
                int top = 1;
                do {
                    top = stack[--stackSize];
                    scc[top] = sccn;
                } while (top != from);
            }
        }
    }
}
