package hope.graph;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code_01_CreateGraphByList
 * @date 2023年11月17日 10:09
 */
public class Code01_CreateGraphByStart {
    public static int MAXN = 11; // 点的最大数量
    public static int MAXM = 21; // 边的最大数量

    public static int[] head = new int[MAXN]; // 下标是点的编号，值表示当前点的头边号
    public static int[] next = new int[MAXM]; // 下标表示边号，值表示下一条边的编号
    public static int[] to = new int[MAXM];// 下标表示边的编号，值表示去往的点
    // 如果边有权重，那么需要这个数组
    public static int[] weight = new int[MAXM];

    public static int cnt;

    public static void build(int n) {
        cnt = 1;
        Arrays.fill(head, 1, n + 1, 0);
    }

    public static void addEdge(int u, int v, int w) {
        next[cnt] = head[u];
        to[cnt] = v;
        weight[cnt] = w;
        head[u] = cnt++;
    }

    public static void create(int[][] edges) {
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1], edge[2]);
            addEdge(edge[1], edge[0], edge[2]);
        }
    }

    public static void print(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(i + "(邻居、边权) : ");
            for (int ei = head[i]; ei > 0; ei = next[ei]) {
                System.out.print("(" + to[ei] + "," + weight[ei] + ") ");
            }
        }
    }
}
