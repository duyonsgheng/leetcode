package custom.code_2023_02;

/**
 * @ClassName LeetCode_1559
 * @Author Duys
 * @Description
 * @Date 2023/2/14 9:29
 **/
// 1559. 二维网格图中探测环
public class LeetCode_1559 {
    // 、既然找环，那么来到一个位置就看看子的左边和上边是不是连在一起的，并且连载一起的长度是不是大于等于3了
    public boolean containsCycle(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        UnionFind uf = new UnionFind(row * col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char c = grid[i][j];
                int index = index(i, j, col);
                int left = index(i, j - 1, col);
                int up = index(i - 1, j, col);
                // 我的左边和上边已经是一个集合了，并且数量是大于等于3的，那么加上我就一定满足了
                if (i > 0 && j > 0 && c == grid[i - 1][j] && c == grid[i][j - 1] && uf.isOnce(left, up) && uf.size(left) >= 3) {
                    return true;
                }
                // 否则，该怎么合并就合并去
                if (i > 0 && c == grid[i - 1][j]) {
                    uf.union(index, up);
                }
                if (j > 0 && c == grid[i][j - 1]) {
                    uf.union(index, left);
                }
            }
        }
        return false;
    }

    public int index(int x, int y, int col) {
        return x * col + y;
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

        public int size(int i) {
            return size[find(i)];
        }

        public boolean isOnce(int a, int b) {
            return find(a) == find(b);
        }

        public int find(int i) {
            int hi = 0;
            while (parent[i] != i) {
                help[hi++] = i;
                i = parent[i];
            }
            while (hi != 0) {
                parent[help[--hi]] = i;
            }
            return i;
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
