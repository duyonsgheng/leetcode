package week.week_2023_07_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName Code_05_LeetCode_1489
 * @date 2023年07月13日
 */
// 1489. 找到最小生成树里的关键边和伪关键边
// https://leetcode.cn/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/
public class Code_05_LeetCode_1489 {
    // 首先超级难题
    // 用到了并查集
    // 用到了桥-tarjan算法
    // 最小生成树
    public static int MAXN = 101;

    public static int MAXM = 201;
    // 边状态的记录
    // status[ei] = 0，代表ei这个边既不是关键边也不是伪关键边
    // status[ei] = 1，代表ei这个边是伪关键边
    // status[ei] = 2，代表ei这个边是关键边
    public static int[] status = new int[MAXM];
    // 并查集相关
    public static int[] father = new int[MAXN];
    public static int[] size = new int[MAXN];
    public static int[] help = new int[MAXN];
    public static int sets = 0;

    public static void initUnion(int n) {
        for (int i = 0; i < n; i++) {
            father[i] = i;
            size[i] = 1;
        }
        sets = n;
    }

    public static int find(int x) {
        int index = 0;
        while (x != father[x]) {
            help[index++] = x;
            x = father[x];
        }
        while (index != 0) {
            father[help[--index]] = x;
        }
        return x;
    }

    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa == fb) {
            return;
        }
        if (size[fa] >= size[fb]) {
            size[fa] += size[fb];
            father[fb] = fa;
        } else {
            size[fb] += size[fa];
            father[fa] = fb;
        }
        sets--;
    }

    // 边进行重新设定
    public static int[][] edges = new int[MAXM][4];
    public static int m;

    public static void buildEdges(int[][] e) {
        for (int i = 0; i < m; i++) {
            edges[i][0] = i;
            edges[i][1] = e[i][0];
            edges[i][2] = e[i][1];
            edges[i][3] = e[i][2];
        }
        Arrays.sort(edges, 0, m, (a, b) -> a[3] - b[3]);
    }

    // 通过集合编号建图相关
    // 链式前向星建图
    // 为啥用这玩意儿建图？没啥，就是想秀
    public static int[] head = new int[MAXN];
    public static int[][] info = new int[MAXM][3];
    public static int[] next = new int[MAXM];
    public static int edgeSize;

    public static void buildGraph(int k) {
        for (int i = 0; i < k; i++) {
            head[i] = -1;
            edgeSize = 0;
        }
    }

    public static void addEdge(int a, int b, int ei) {
        next[edgeSize] = head[a];
        info[edgeSize][0] = ei;
        info[edgeSize][1] = a;
        info[edgeSize][2] = b;
        head[a] = edgeSize++;
    }

    // 哈希表相关
    public static int[] id = new int[MAXN];

    // 找桥相关
    public static int[] dfn = new int[MAXN];
    public static int[] low = new int[MAXN];
    public static int cnt;

    public static void findBridge(int k) {
        Arrays.fill(dfn, 0, k, 0);
        Arrays.fill(low, 0, k, 0);
        cnt = 0;
        for (int init = 0; init < k; init++) {
            if (dfn[init] == 0) {
                tarjan(init, init, -1, -1);
            }
        }
    }

    public static void tarjan(int init, int cur, int father, int ei) {
        dfn[cur] = low[cur] = ++cnt;
        for (int i = head[cur]; i != -1; i = next[i]) {
            int edgei = info[i][0];
            int nodei = info[i][2];
            if (nodei != father) {
                if (dfn[nodei] == 0) {
                    tarjan(init, nodei, cur, edgei);
                    low[cur] = Math.min(low[cur], low[nodei]);
                } else {
                    low[cur] = Math.min(low[cur], dfn[nodei]);
                }
            }
        }
        if (low[cur] == dfn[cur] && cur != init) {
            status[ei] = 2;
        }
    }

    public static List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] e) {
        m = e.length;
        Arrays.fill(status, 0, m, 0);
        initUnion(n);
        buildEdges(e);
        List<Integer> bridge = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();
        int start = 0;
        while (sets != 1) {
            int end = start + 1;
            while (end < m && edges[start][3] == edges[end][3]) {
                end++;
            }
            connect(start, end);
            for (int i = start; i < end; i++) {
                int ei = edges[i][0];
                if (status[ei] == 2) {
                    bridge.add(ei);
                } else if (status[ei] == 1) {
                    pseudo.add(ei);
                }
                union(edges[i][1], edges[i][2]);
            }
            start = end;
        }
        return Arrays.asList(bridge, pseudo);
    }

    public static void connect(int start, int end) {
        for (int i = start; i < end; i++) {
            id[find(edges[i][1])] = -1;
            id[find(edges[i][2])] = -1;
        }
        int k = 0;
        for (int i = start; i < end; i++) {
            if (id[find(edges[i][1])] == -1) {
                id[find(edges[i][1])] = k++;
            }
            if (id[find(edges[i][2])] == -1) {
                id[find(edges[i][2])] = k++;
            }
        }
        buildGraph(k);
        for (int i = start; i < end; i++) {
            int index = edges[i][0];
            int a = id[find(edges[i][1])];
            int b = id[find(edges[i][2])];
            if (a != b) {
                status[index] = 1;
                addEdge(a, b, index);
                addEdge(b, a, index);
            }
        }
        findBridge(k);
        // 处理重复连接
        // 什么是重复连接？不是自己指向自己，那叫自环
        // 重复连接指的是:
        // 集合a到集合b有一条边，边的序号是p
        // 于是，a的邻接表里有(p,b)，b的邻接表里有(p,a)
        // 集合a到集合b又有一条边，边的序号是t
        // 于是，a的邻接表里有(t,b)，b的邻接表里有(t,a)
        // 那么p和t都是重复链接，因为单独删掉p或者t，不会影响联通性
        // 而在求桥的模版中，是默认没有重复链接的
        // 如果有重复链接就直接用模版，那么会出现重复链接被认为是桥的情况
        // 所以这里要单独判断，如果有重复链接被设置成了桥，要把它改成伪关键边的状态
        Arrays.sort(info, 0, edgeSize, (a, b) -> a[1] != b[1] ? (a[1] - b[1]) : (a[2] - b[2]));
        int right, left = 0;
        while (left < edgeSize) {
            right = left + 1;
            while (right < edgeSize && info[left][1] == info[right][1]) {
                right++;
            }
            for (int i = left + 1; i < right; i++) {
                if (info[i][2] == info[i - 1][2]) {
                    status[info[i][0]] = 1;
                    status[info[i - 1][0]] = 1;
                }
            }
            left = right;
        }
    }
}
