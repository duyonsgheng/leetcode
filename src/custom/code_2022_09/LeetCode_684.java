package custom.code_2022_09;

/**
 * @ClassName LeetCode_684
 * @Author Duys
 * @Description
 * @Date 2022/9/7 11:24
 **/
// 684.冗余的链接
public class LeetCode_684 {


    // 并查集的简单应用
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind un = new UnionFind(n);
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if (un.isOnce(a, b)) {
                return edge;
            }
            un.union(a, b);
        }
        return null;
    }

    class UnionFind {
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

        public boolean isOnce(int a, int b) {
            return find(a) == find(b);
        }

        private int find(int x) {
            int helpIndex = 0;
            while (x != parent[x]) {
                x = parent[x];
                help[helpIndex++] = x;
            }
            for (helpIndex--; helpIndex >= 0; helpIndex--) {
                parent[help[helpIndex]] = x;
            }
            return x;
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
    }
}
