package week.week_2021_12_01;

/**
 * @ClassName Code_03_LeetCode_685
 * @Author Duys
 * @Description
 * @Date 2022/4/19 13:57
 **/
// https://leetcode-cn.com/problems/redundant-connection-ii/
//在本问题中，有根树指满足以下条件的 有向 图。该树只有一个根节点，所有其他节点都是该根节点的后继。
//该树除了根节点之外的每一个节点都有且只有一个父节点，而根节点没有父节点。
//输入一个有向图，该图由一个有着 n 个节点（节点值不重复，从 1 到 n）的树及一条附加的有向边构成。
//附加的边包含在 1 到 n 中的两个不同顶点间，这条附加的边不属于树中已存在的边。
//结果图是一个以边组成的二维数组edges 。 每个元素是一对 [ui, vi]，用以表示 有向 图中连接顶点 ui 和顶点 vi 的边，其中 ui 是 vi 的一个父节点。
//返回一条能删除的边，使得剩下的图是有 n 个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/redundant-connection-ii
public class Code_03_LeetCode_685 {


    public static int[] findRedundantDirectedConnection(int[][] edges) {
        // 点的数量
        int n = edges.length;
        // 建立并查集
        UnionFind uf = new UnionFind(n);

        // pre[i] = 0 来到i节点是第一次
        // pre[1] = 1 来到i节点，之前来过，是从6过来的
        int[] pre = new int[n + 1];
        // 如果没有入度为2的点，那么first = null,second = null;
        // 如果有入度为2的点，那么也只能有一个
        // 例如：first = [2,6] ，第一次是从2来的， second =[3,6]，第二次是从3来的
        int[] first = null;
        int[] second = null;

        // 有没有环
        int[] circle = null;
        // 遍历每条边
        for (int i = 0; i < n; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            // 不止一次来过这个点
            if (pre[to] != 0) {
                first = new int[]{pre[to], to};
                second = edges[i];
            } else {
                // 第一次来到to这个点
                pre[to] = from;
                if (uf.seam(from, to)) {
                    circle = edges[i];
                } else {
                    uf.union(from, to);
                }
            }
        }
        // first!=null 有入度为2的点
        return first != null ? (circle != null ? first : second) : circle;
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;

        public UnionFind(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            help = new int[n + 1];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int i) {
            int helpIndex = 0;
            while (i != parent[i]) {
                help[helpIndex++] = i;
                i = parent[i];
            }
            while (helpIndex > 0) {
                parent[help[--helpIndex]] = i;
            }
            return i;
        }

        public boolean seam(int i, int j) {
            return find(i) == find(j);
        }

        public void union(int i, int j) {
            int ip = find(i);
            int jp = find(j);
            if (ip == jp) {
                return;
            }
            if (size[jp] > size[ip]) {
                parent[ip] = jp;
                size[jp] = size[jp] + size[ip];
            } else {
                parent[jp] = ip;
                size[ip] = size[ip] + size[jp];
            }
        }
    }
}
