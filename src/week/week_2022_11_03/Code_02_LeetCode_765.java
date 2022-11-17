package week.week_2022_11_03;

/**
 * @ClassName Code_02_LeetCode_765
 * @Author Duys
 * @Description
 * @Date 2022/11/17 9:44
 **/
// 765. 情侣牵手
public class Code_02_LeetCode_765 {
    // 思路：
    // 注意题意给出的信息，情侣是 0 1 一对，2 3一对，那么整个流程就像极了下标循环怼的问题
    // 但是下标循环怼的时候，会算多，比如 下标循环怼的时候我们期望 0在0位置，1在1位置，但是本题只需要在一块挨着就行
    // 所以我们得想个办法来规避这个问题
    // 如果 abcba 这种情况，他们三个组曾了一个环，只有三个元素 abc，所以交换次数 m-1就够了，m是元素个数
    // 所以我们需要算出整个排列有几个环，可以借助并查集了
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        UnionFind uf = new UnionFind(n / 2);
        for (int i = 0; i < n; i += 2) {
            // 0 ，1 一对
            // 2 ，3 一对 那么他们可以算作一个 就是 /2
            uf.union(row[i] / 2, row[i + 1] / 2);
        }
        // 3对 交换次数 3-1
        // 5对 交换次数 5-1
        // ............
        // 就是 n/2 对 - 有多少个集合
        return n / 2 - uf.sets();
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

        public int sets() {
            return sets;
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) {
                return;
            }
            if (size[px] >= size[py]) {
                size[px] += size[py];
                parent[py] = px;
            } else {
                size[py] += size[px];
                parent[px] = py;
            }
            sets--;
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
