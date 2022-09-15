package week.week_2022_05_02;

/**
 * @ClassName Code_05_RedAndWhiteSquares
 * @Author Duys
 * @Description
 * @Date 2022/5/16 10:30
 **/
// 来自网易
// 小红拿到了一个大立方体，该大立方体由1*1*1的小方块拼成，初始每个小方块都是白色。
// 小红可以每次选择一个小方块染成红色
// 每次小红可能选择同一个小方块重复染色
// 每次染色以后，你需要帮小红回答出当前的白色连通块数
// 如果两个小方块共用同一个面，且颜色相同，则它们是连通的
// 给定n、m、h，表示大立方体的长、宽、高
// 给定k次操作，每一次操作用(a, b, c)表示在大立方体的该位置进行染色
// 返回长度为k的数组，表示每一次操作后，白色方块的连通块数
// n * m * h <= 10 ^ 5，k <= 10 ^ 5
public class Code_05_RedAndWhiteSquares {

    // 这题一看就和之前遇到过的一个问题很像，打砖块问题
    // 知识点是并查集，但是这个题我们需要逆推，然后依次来减少点，得出答案，
    // 只是需要把三维的换算成一维来做
    public static int[] blocks(int n, int m, int h, int[][] ops) {
        int p = ops.length;
        int[][][] red = new int[n][m][h];
        for (int[] op : ops) {
            red[op[0]][op[1]][op[2]]++;
        }
        // 依次建好了并查集
        UnionFind uf = new UnionFind(n, m, h, red);
        int ans[] = new int[p];
        for (int i = p - 1; p >= 0; p--) {
            ans[i] = uf.sets;
            int x = ops[i][0];
            int y = ops[i][1];
            int z = ops[i][2];
            // 从后往前逆推过程了，有一个点颜色清除了，需要去和周围的合并了
            if (--red[x][y][z] == 0) {
                uf.finger(x, y, z);
            }
        }
        return ans;
    }

    public static class UnionFind {
        public int n;
        public int m;
        public int h;
        public int[] father;
        public int[] size;
        public int[] help;
        public int sets;

        public UnionFind(int a, int b, int c, int[][][] red) {
            n = a;
            m = b;
            h = c;
            int len = n * m * h; // 转一维使用
            father = new int[len];
            size = new int[len];
            help = new int[len];
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    for (int z = 0; z < h; z++) {
                        // 这个点是0，合并去吧
                        if (red[x][y][z] == 0) {
                            finger(x, y, z);
                        }
                    }
                }
            }
        }

        public void finger(int x, int y, int z) {
            int index = index(x, y, z);
            father[index] = index;
            sets++;
            size[index] = 1;
            // 6个面都去搞一下
            union(index, x - 1, y, z);
            union(index, x + 1, y, z);
            union(index, x, y - 1, z);
            union(index, x, y + 1, z);
            union(index, x, y, z - 1);
            union(index, x, y, z + 1);
        }

        public int index(int x, int y, int z) {
            return z * n * m + y * n + x;
        }

        private void union(int i, int x, int y, int z) {
            if (x < 0 || x == n || y < 0 || y == m || z < 0 || z == h) {
                return;
            }
            int tar = index(x, y, z);
            if (size[tar] == 0) {
                return;
            }
            i = find(i);
            tar = find(tar);
            if (i == tar) {
                return;
            }
            if (size[i] > size[tar]) {
                size[i] += size[tar];
                father[tar] = i;
            } else {
                size[tar] += size[i];
                father[i] = tar;
            }
            sets--;
        }

        private int find(int index) {
            int size = 0;
            // 一直找自己的父亲
            while (index != father[index]) {
                help[size++] = index;
                index = father[index];
            }
            // 沿途的都记录下来
            while (size > 0) {
                father[help[size--]] = index;
            }
            return index;
        }
    }
}
