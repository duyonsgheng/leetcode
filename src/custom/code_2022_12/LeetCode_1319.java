package custom.code_2022_12;

/**
 * @ClassName LeetCode_1319
 * @Author Duys
 * @Description
 * @Date 2022/12/9 9:54
 **/
// 1319. 连通网络的操作次数
public class LeetCode_1319 {

    // 并查集
    public static int makeConnected(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n);
        int more = 0; // 多余的网线
        for (int[] co : connections) {
            if (uf.isOnce(co[0], co[1])) {
                more++;
            } else {
                uf.union(co[0], co[1]);
            }
        }
        // 有几个集合。先用 多余的网线，把集合搞到一堆 至少需要sets-1条线
        int sets = uf.getSets();
        if (sets - 1 > more) {
            return -1;
        }
        return sets - 1;
    }

    static class UnionFind {
        int[] parent;
        int[] size;
        int[] help;
        int sets;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            sets = n;
        }

        public boolean isOnce(int a, int b) {
            return find(a) == find(b);
        }

        public int getSets() {
            return sets;
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
            sets--;
        }

        public int find(int a) {
            int hi = 0;
            while (a != parent[a]) {
                help[hi++] = a;
                a = parent[a];
            }
            for (hi--; hi >= 0; hi--)
                parent[help[hi]] = a;
            return a;
        }
    }

    public static void main(String[] args) {
        // n = 4, connections = [[0,1],[0,2],[1,2]]   1
        // n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]  2
        // n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]  -1
        // n = 5, connections = [[0,1],[0,2],[3,4],[2,3]] 0
        System.out.println(makeConnected(4, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
        System.out.println(makeConnected(6, new int[][]{{0, 1}, {0, 2}, {1, 2}, {0, 3}, {1, 3}}));
        System.out.println(makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}}));
        System.out.println(makeConnected(5, new int[][]{{0, 1}, {0, 2}, {3, 4}, {2, 3}}));
    }
}
