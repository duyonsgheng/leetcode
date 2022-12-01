package custom.code_2022_11;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_1254
 * @Author Duys
 * @Description
 * @Date 2022/11/29 18:00
 **/
// 1254. 统计封闭岛屿的数目
public class LeetCode_1254 {
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int size = m * n;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    count++;
                }
            }
        }
        UnionFind uf = new UnionFind(size, count);
        // 先看边界
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                uf.init(i * n);
            }
            if (grid[i][n - 1] == 0) {
                uf.init(i * n + n - 1);
            }
        }
        for (int j = 1; j < n - 1; j++) {
            if (grid[0][j] == 0) {
                uf.init(j);
            }
            if (grid[m - 1][j] == 0) {
                uf.init((m - 1) * n + j);
            }
        }
        // 再看中间
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    if (i > 0 && grid[i - 1][j] == 0) {
                        uf.union(i * n + j, (i - 1) * n + j);
                    }
                    if (j > 0 && grid[i][j - 1] == 0) {
                        uf.union(i * n + j, i * n + j - 1);
                    }
                }
            }
        }
        return uf.getCount();
    }

    class UnionFind {
        private int count;
        private int[] parent;
        private int[] help;
        private int[] sizes;
        private boolean[] onces;

        public UnionFind(int n, int c) {
            parent = new int[n];
            help = new int[n];
            sizes = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                sizes[i] = 1;
            }
            onces = new boolean[n];
            count = c;
        }

        public void init(int x) {
            if (!onces[x]) {
                parent[x] = 0;
                onces[x] = true;
                count--;
            }
        }

        public int getCount() {
            return count;
        }

        public void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) {
                return;
            }
            onces[pa] |= onces[pb];
            if (sizes[pa] > sizes[pb]) {
                parent[pb] = pa;
                onces[pa] |= onces[pb];
            } else if (sizes[pa] < sizes[pb]) {
                parent[pa] = pb;
                onces[pb] |= onces[pa];
            } else {
                parent[pb] = pa;
                onces[pa] |= onces[pa];
                sizes[pa]++;
            }
            count--;
        }

        public int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--)
                parent[help[hi]] = i;
            return i;
        }
    }
}
