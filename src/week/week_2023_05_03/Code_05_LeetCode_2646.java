package week.week_2023_05_03;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_05_LeetCode_2646
 * @Author Duys
 * @Description
 * @Date 2023/5/18 10:15
 **/
// 2646. 最小化旅行的价格总和
public class Code_05_LeetCode_2646 {

    // 名副其实的压轴题
    // 1.首先树形dp跑不了，当前节点减半了，那么子节点一定不能减半；当前节点不减半，那么子节点可以减半可以不减半，选择子节点中最小的，
    // 2.旅行的线路，我们需要知道当前旅行线路中两个节点的最小公共祖先，当前线路上每个节点次数都要加1，怎么能快速得到呢？答案是差分，这点是很难想到的，
    // 我们给两个节点+1，给两个节点的最低公共祖先节点-1，为了保证次数正确，还需要给最低公共祖先的父节点-1，
    // 3.并查集来搞定合并问题，并且给节点打上tag
    // 开始
    public static int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        // 1.建图
        List<List<Integer>> graph = new ArrayList<>();
        // 当前节点和[a,b]中的a有查询，如果存在把答案填到b位置
        List<List<int[]>> queries = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            queries.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int m = trips.length;
        // 最低公共祖先节点
        int[] lcs = new int[m];
        // 每个节点的父节点
        int[] fathers = new int[n];
        for (int i = 0; i < m; i++) {
            if (trips[i][0] == trips[i][1]) {
                lcs[i] = trips[i][0]; // 只有一个点
            } else {
                queries.get(trips[i][0]).add(new int[]{trips[i][1], i});
                queries.get(trips[i][1]).add(new int[]{trips[i][0], i});
            }
        }
        UnionFind uf = new UnionFind(n);
        tarjan(graph, 0, -1, uf, queries, fathers, lcs);
        int[] cnts = new int[n];
        // 现在就把所有节点次数填好了
        for (int i = 0; i < m; i++) {
            cnts[trips[i][0]]++;
            cnts[trips[i][1]]++;
            cnts[lcs[i]]--;
            if (fathers[lcs[i]] != -1) {
                cnts[fathers[lcs[i]]]--;
            }
        }
        // 收集节点次数
        dfs(graph, 0, -1, cnts);
        // 开始树形dp，看看当前节点减半和不减半
        int[] ans = dp(graph, 0, -1, cnts, price);
        return Math.min(ans[0], ans[1]);
    }

    // graph - 图
    // cur -当前节点，father-当前节点的父节点
    // queries 查询数组
    // fathers 每个节点的父节点，
    // lcs 查询节点的最低公共祖先数组
    public static void tarjan(List<List<Integer>> graph, int cur, int father, UnionFind uf, List<List<int[]>> queries, int[] fathers, int[] lcs) {
        fathers[cur] = father;
        for (int next : graph.get(cur)) {
            if (next == father) {
                continue;
            }
            // 先去把自己的子节点处理好
            tarjan(graph, next, cur, uf, queries, fathers, lcs);
            // 把当前节点和子节点合并
            uf.union(cur, next);
            // 设置当前节点的tag为自己
            uf.setTag(cur, cur);
        }
        // 现在开始收集cur节点的问题
        for (int[] query : queries.get(cur)) {
            // query[0] 和 cur 存在查询，如果已经有答案了，填在query[1]上去
            int tag = uf.getTag(query[0]);
            if (tag == -1) {
                continue;
            }
            lcs[query[1]] = tag;
        }
    }

    // 收集所有节点的次数
    public static void dfs(List<List<Integer>> graph, int cur, int father, int[] cnts) {
        for (int next : graph.get(cur)) {
            if (next == father) {
                continue;
            }
            dfs(graph, next, cur, cnts);
            cnts[cur] += cnts[next];
        }
    }

    public static int[] dp(List<List<Integer>> graph, int cur, int father, int[] cnts, int[] price) {
        // 当前节点价值不减半
        int no = price[cur] * cnts[cur];
        // 当前节点减半
        int yes = (price[cur] / 2) * cnts[cur];
        // 当前节点价值减半
        for (int next : graph.get(cur)) {
            if (next == father) {
                continue;
            }
            // 先去找子节点的答案
            int[] nextAns = dp(graph, next, cur, cnts, price);
            // 设置当前节点的答案
            // 当前的答案，如果不减半，那么选择子节点 减半和不减半 最小的
            no += Math.min(nextAns[0], nextAns[1]);
            // 如果减半，那么只能选择子节点不减半的
            yes += nextAns[0];
        }
        return new int[]{no, yes};
    }

    public static class UnionFind {
        public int[] parent;
        public int[] size;
        public int[] tag;
        public int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            tag = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
                tag[i] = -1;
            }
        }

        public void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) {
                return;
            }
            if (size[pa] >= size[pb]) {
                parent[pb] = pa;
                size[pa] += size[pb];
            } else {
                parent[pa] = pb;
                size[pb] += size[pa];
            }
        }

        public int find(int x) {
            int hi = 0;
            while (x != parent[x]) {
                help[hi++] = x;
                x = parent[x];
            }
            while (hi > 0) {
                parent[help[--hi]] = x;
            }
            return x;
        }

        public void setTag(int i, int t) {
            tag[find(i)] = t;
        }

        public int getTag(int i) {
            return tag[find(i)];
        }
    }
}
