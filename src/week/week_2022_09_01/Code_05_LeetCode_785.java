package week.week_2022_09_01;

/**
 * @ClassName Code_05_LeetCode_785
 * @Author Duys
 * @Description
 * @Date 2022/9/8 12:30
 **/
//  https://leetcode.cn/problems/is-graph-bipartite/
public class Code_05_LeetCode_785 {
    // 并查集
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        UnionFind uf = new UnionFind(n);
        for (int[] arr : graph) {
            for (int i = 1; i < arr.length; i++) {
                uf.union(arr[i - 1], arr[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j : graph[i]) {
                if (uf.isOnce(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int a, int b) {
            int ap = find(a);
            int bp = find(b);
            if (ap == bp) {
                return;
            }
            if (size[ap] >= size[bp]) {
                size[ap] += size[bp];
                parent[bp] = ap;
            } else {
                size[bp] += size[ap];
                parent[ap] = bp;
            }
        }

        public boolean isOnce(int a, int b) {
            return find(a) == find(b);
        }

        private int find(int x) {
            int hi = 0;
            while (x != parent[x]) {
                x = parent[x];
                help[hi++] = x;
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = x;
            }
            return x;
        }
    }
}
