package week.week_2023_02_02;

import java.util.Arrays;

/**
 * @ClassName Code_04_LeetCode_928
 * @Author Duys
 * @Description
 * @Date 2023/2/9 10:12
 **/
// 928. 尽量减少恶意软件的传播 II
public class Code_04_LeetCode_928 {

    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        // 开始节点
        boolean[] start = new boolean[n];
        for (int i : initial) {
            start[i] = true;
        }
        // 忽略掉开始节点，然后建立并查集
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 有路，且都不是开始节点，则建立连接
                if (graph[i][j] == 1 && !start[i] && !start[j]) {
                    uf.union(i, j);
                }
            }
        }
        // 设置代表节点
        // 所有非感染点，该链接都链接了！
        // 0,6,7,8 是一个集合
        // 6被选成了代表点，无法控制，只由并查集自己决定
        // infect[6] == -1 ，目前这个集合没有感染源
        // infect[6] == -2 ，目前这个集合已经发现了不只一个感染源
        // infect[6] == x x>=0，目前这个集合已经发现了一个感染源，是x点
        int[] infect = new int[n];
        Arrays.fill(infect, -1);
        for (int v : initial) {
            // 当前感染点是v
            // 跟v连接的点是next
            for (int next = 0; next < n; next++) {
                if (!(v != next && !start[next] && graph[v][next] == 1)) {
                    continue;
                }
                // next的代表节点
                int p = uf.find(next);
                // 如果还没代表节点，那么就用感染的开始点作为代表节点
                if (infect[p] == -1) {
                    infect[p] = v;
                } else {
                    // 说明不止一个感染节点
                    if (infect[p] != -2 && infect[p] != v) {
                        infect[p] = -2;
                    }
                    // 如果 =-2 不用管了，如果是 =v的也不用管，之前已经设置过了
                }
            }
        }
        // 统计代表节点管着多少节点
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            if (infect[i] >= 0) {
                cnt[infect[i]] += uf.size[i];
            }
        }
        Arrays.sort(initial);
        int ans = initial[0];
        int count = cnt[ans];
        for (int i : initial) {
            if (cnt[i] > count) {
                ans = i;
                count = cnt[i];
            }
        }
        return ans;
    }

    public class UnionFind {
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

        public int find(int a) {
            int hi = 0;
            while (parent[a] != a) {
                help[hi++] = a;
                a = parent[a];
            }
            while (hi != 0) {
                parent[help[--hi]] = a;
            }
            return a;
        }
    }
}
