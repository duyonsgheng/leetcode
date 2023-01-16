package week.week_2023_01_02;

/**
 * @ClassName Code_01_LeetCode_893
 * @Author Duys
 * @Description
 * @Date 2023/1/12 10:18
 **/
// 839. 相似字符串组
public class Code_01_LeetCode_893 {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (uf.isOnce(i, j)) {
                    continue;
                }
                int diff = 0;
                for (int c = 0; c < m && diff < 3; c++) {
                    if (strs[i].charAt(c) != strs[j].charAt(c)) {
                        diff++;
                    }
                }
                if (diff == 0 || diff == 2) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getSets();
    }

    class UnionFind {
        int[] parent;
        int[] size;
        int[] help;
        int sets;

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

        public int find(int x) {
            int hi = 0;
            while (x != parent[x]) {
                help[hi++] = x;
                x = parent[x];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = x;
            }
            return x;
        }

        public boolean isOnce(int a, int b) {
            return find(a) == find(b);
        }

        public int getSets() {
            return sets;
        }
    }
}
