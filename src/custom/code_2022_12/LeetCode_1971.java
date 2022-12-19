package custom.code_2022_12;

/**
 * @ClassName LeetCode_1971
 * @Author Duys
 * @Description
 * @Date 2022/12/19 8:50
 **/
// 1971. 寻找图中是否存在路径
public class LeetCode_1971 {
    // 6
    //[[0,1],[0,2],[3,5],[5,4],[4,3]]
    //0
    //5
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.isOnce(source, destination);
    }

    class UnionFind {
        int[] parent;
        int[] size;
        int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int a) {
            int hi = 0;
            while (a != parent[a]) {
                help[hi++] = a;
                a = parent[a];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = a;
            }
            return a;
        }

        public boolean isOnce(int a, int b) {
            return find(a) == find(b);
        }

        public void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) {
                return;
            }
            if (size[pa] >= size[pb]) {
                size[pa] += size[pb];
                parent[pb] = pa;
            } else {
                size[pb] += size[pa];
                parent[pa] = pb;
            }
        }
    }
}
