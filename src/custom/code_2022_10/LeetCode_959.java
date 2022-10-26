package custom.code_2022_10;

/**
 * @ClassName LeetCode_959
 * @Author Duys
 * @Description
 * @Date 2022/10/24 16:34
 **/
// 959. 由斜杠划分区域
public class LeetCode_959 {

    // 并查集
    // 给每一个小的正方形编号切分的四个三角形编号
    // 上边的 0 右边的 1  下边的 2 左边的 3
    // 苏伦如何且分 下摆你的2号一定是和下面的方格的0号三角形合并的，右边的1号一定是和右边的方格3号三角形合并
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        UnionFind uf = new UnionFind(4 * n * n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 先把右边和下边的搞好
                int index = i * n + j;
                if (i < n - 1) {
                    int down = index + n;
                    uf.union(index * 4 + 2, down * 4);
                }
                if (j < n - 1) {
                    int right = index + 1;
                    uf.union(index * 4 + 1, right * 4 + 3);
                }
                char cur = grid[i].charAt(j);
                if (cur == '/') { //  1 2 合并 ， 0 3 合并
                    uf.union(index * 4 + 1, index * 4 + 2);
                    uf.union(index * 4, index * 4 + 3);
                } else if (cur == '\\') { // 0 1 合并，2 3合并
                    uf.union(index * 4 + 3, index * 4 + 2);
                    uf.union(index * 4, index * 4 + 1);
                } else { // 三个全部合并
                    uf.union(index * 4, index * 4 + 1);
                    uf.union(index * 4 + 1, index * 4 + 2);
                    uf.union(index * 4 + 2, index * 4 + 3);
                }
            }
        }
        return uf.sets();
    }

    class UnionFind {
        private int[] parent;
        private int[] help;
        private int[] size;
        private int stes;

        public UnionFind(int n) {
            parent = new int[n];
            help = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            stes = n;
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (py == px) {
                return;
            }
            if (size[px] >= size[py]) {
                size[px] += size[py];
                parent[py] = px;
            } else {
                size[py] += size[px];
                parent[px] = py;
            }
            stes--;
        }

        public int sets() {
            return stes;
        }

        private int find(int x) {
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
    }
}
