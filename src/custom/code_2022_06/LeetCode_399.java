package custom.code_2022_06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_399
 * @Author Duys
 * @Description
 * @Date 2022/6/13 13:37
 **/
// 399. 除法求值
// 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
//每个 Ai 或 Bi 是一个表示单个变量的字符串。
//另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
//返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
//注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
//链接：https://leetcode.cn/problems/evaluate-division
public class LeetCode_399 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = equations.size();
        UnionFind uf = new UnionFind(n << 1);
        Map<String, Integer> map = new HashMap<>();
        int id = 0;
        for (int i = 0; i < n; i++) {
            List<String> list = equations.get(i);
            String v1 = list.get(0);
            String v2 = list.get(1);
            if (!map.containsKey(v1)) {
                map.put(v1, id++);
            }
            if (!map.containsKey(v2)) {
                map.put(v2, id++);
            }
            uf.union(map.get(v1), map.get(v2), values[i]);
        }
        int m = queries.size();
        double[] ans = new double[m];
        for (int i = 0; i < m; i++) {
            String v1 = queries.get(i).get(0);
            String v2 = queries.get(i).get(1);
            Integer id1 = map.get(v1);
            Integer id2 = map.get(v2);
            if (id1 == null || id2 == null) {
                ans[i] = -1.0;
            } else {
                ans[i] = uf.isConnected(id1, id2);
            }
        }
        return ans;
    }

    public static class UnionFind {
        private int[] parent;
        private double[] wight;

        public UnionFind(int n) {
            parent = new int[n];
            wight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                wight[i] = 1.0d;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                wight[x] *= wight[origin];
            }
            return parent[x];
        }

        public void union(int i, int j, double value) {
            int fi = find(i);
            int fj = find(j);
            if (fi == fj) {
                return;
            }
            parent[fi] = fj;
            wight[fi] = wight[j] * value / wight[i];
        }

        public double isConnected(int i, int j) {
            int fi = find(i);
            int fj = find(j);
            if (fi == fj) {
                return wight[i] / wight[j];
            }
            return -1.0d;
        }
    }
}
