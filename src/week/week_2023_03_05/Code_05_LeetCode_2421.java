package week.week_2023_03_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_05_LeetCode_2421
 * @Author Duys
 * @Description
 * @Date 2023/3/30 9:52
 **/
// 2421. 好路径的数目
public class Code_05_LeetCode_2421 {
    // 思路
    // 并查集，给每一个集合打标签，记录一下每个集合的代表节点

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        // 开始建图
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        // 所有的节点
        int[][] nodes = new int[n][2];
        for (int i = 0; i < n; i++) {
            nodes[i][0] = i;
            nodes[i][1] = vals[i];
        }
        // 节点值小的在前面
        Arrays.sort(nodes, (a, b) -> a[1] - b[1]);
        UnionFind uf = new UnionFind(n);
        // 打标签，每个位置的代表节点是谁
        int[] indexs = new int[n];
        // 最开始，自己代表自己
        for (int i = 0; i < n; i++) {
            indexs[i] = i;
        }
        // 记录最大值的次数词频统计
        int[] maxCnts = new int[n];
        Arrays.fill(maxCnts, 1);
        int ans = n;// 最开始单个节点都满足
        for (int[] node : nodes) {
            int curi = node[0];// 当前i
            int curv = node[1];// 当前值
            int curPi = uf.find(curi); // 当前节点的代表节点是谁
            int curMaxIndex = indexs[curPi]; // 当前代表节点最大值是
            // 邻居
            for (int nexti : graph.get(curi)) {
                int nextv = vals[nexti];
                // 邻居节点代表节点
                int nextPi = uf.find(nexti);
                // 当前节点和邻居节点不是同一个，并且当前节点的值是大于等于邻居节点的值
                if (nextPi != curPi && curv >= nextv) {
                    int nextMaxIndex = indexs[nextPi];
                    // 当前节点和邻居节点代表节点最大值是相同的，可以结算好路径了
                    if (curv == vals[nextMaxIndex]) {
                        ans += maxCnts[curMaxIndex] * maxCnts[nextMaxIndex];
                        maxCnts[curMaxIndex] += maxCnts[nextMaxIndex];
                    }
                    int pi = uf.union(curi, nexti);
                    indexs[pi] = curMaxIndex;
                }
            }
        }
        return ans;
    }

    class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            int h = 0;
            while (x != parent[x]) {
                help[h++] = x;
                x = parent[x];
            }
            while (h > 0) {
                parent[help[--h]] = x;
            }
            return x;
        }

        // 返回代表节点
        public int union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) {
                return pa;
            }
            if (size[pa] >= size[pb]) {
                size[pa] += size[pb];
                parent[pb] = pa;
                return pa;
            } else {
                size[pb] += size[pa];
                parent[pa] = pb;
                return pb;
            }
        }
    }

}
