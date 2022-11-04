package custom.code_2022_11;

/**
 * @ClassName LeetCode_1020
 * @Author Duys
 * @Description
 * @Date 2022/11/3 15:15
 **/
//1020. 飞地的数量
public class LeetCode_1020 {
    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        UnionFind unionFind = new UnionFind(m * n + 2);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    // 从边界的点出发去
                    if (grid[i][j] != 1 || unionFind.query(i * m + j + 1, 0)) {
                        continue;
                    }
                    // 边界上的和0搞到一起去
                    dfs(i, j, n, m, grid, unionFind);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !unionFind.query(i * m + j + 1, 0)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public void dfs(int x, int y, int n, int m, int[][] grid, UnionFind unionFind) {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        unionFind.union(x * m + y + 1, 0);
        for (int[] d : dirs) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }
            if (grid[nx][ny] != 1 || unionFind.query(nx * m + ny + 1, 0)) {
                continue;
            }
            dfs(nx, ny, n, m, grid, unionFind);
        }
    }

    class UnionFind {
        private int[] parent;
        private int[] help;
        private int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            help = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public boolean query(int a, int b) {
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
    }
}
