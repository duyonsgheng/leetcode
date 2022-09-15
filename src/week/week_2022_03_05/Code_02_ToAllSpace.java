package week.week_2022_03_05;

import java.util.Arrays;

/**
 * @ClassName Code_02_ToAllSpace
 * @Author Duys
 * @Description
 * @Date 2022/3/31 10:47
 **/
// 来自微软
// 在N*N的正方形棋盘中，有N*N个棋子，那么每个格子正好可以拥有一个棋子
// 但是现在有些棋子聚集到一个格子上了，比如：
// 2 0 3
// 0 1 0
// 3 0 0
// 如上的二维数组代表，一共3*3个格子
// 但是有些格子有2个棋子、有些有3个、有些有1个、有些没有
// 请你用棋子移动的方式，让每个格子都有一个棋子
// 每个棋子可以上、下、左、右移动，每移动一步算1的代价
// 返回最小的代价
public class Code_02_ToAllSpace {
    // 暴力解答
    public static int minDistance1(int[][] map) {
        int n = 0; // 相对格子来说，总共多余的棋子数量
        int m = 0; // 一个棋子都没有的格子有多少
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                n += Math.max(0, map[i][j] - 1);
                m += map[i][j] == 0 ? 1 : 0;
            }
        }
        if (n != m || n == 0) {
            return 0;
        }
        // 有棋子的格子
        int[][] nodes = new int[n][2];
        // 没有棋子的格子
        int[][] space = new int[m][2];
        n = 0;
        m = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (int k = 2; k <= map[i][j]; k++) {
                    nodes[n][0] = i;
                    nodes[n++][1] = j;
                }
                if (map[i][j] == 0) {
                    space[m][0] = i;
                    space[m++][1] = j;
                }
            }
        }
        return process(nodes, 0, space);
    }

    public static int process(int[][] nodes, int index, int[][] space) {
        int ans = 0;
        if (index == nodes.length) {
            for (int i = 0; i < nodes.length; i++) {
                ans += distance(nodes[i], space[i]);
            }
        } else {
            ans = Integer.MAX_VALUE;
            for (int i = index; i < nodes.length; i++) {
                swap(nodes, index, i);
                ans = Math.min(ans, process(nodes, index + 1, space));
                swap(nodes, index, i);
            }
        }
        return ans;
    }

    public static void swap(int[][] node, int i, int j) {
        int[] tmp = node[i];
        node[i] = node[j];
        node[j] = tmp;
    }

    // 曼哈顿距离
    public static int distance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }


    // KM解答
    public static int minDistance2_KM(int[][] map) {
        int n = 0;
        int m = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                n += Math.max(0, map[i][j] - 1);
                m += map[i][j] == 0 ? 1 : 0;
            }
        }
        if (n != m || n == 0) {
            return 0;
        }
        int[][] nodes = new int[n][2];
        int[][] space = new int[m][2];
        n = 0;
        m = 0;
        // 构建图出来
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (int k = 2; k <= map[i][j]; k++) {
                    nodes[n][0] = i;
                    nodes[n++][1] = j;
                }
                if (map[i][j] == 0) {
                    space[m][0] = i;
                    space[m++][0] = j;
                }
            }
        }
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = -distance(nodes[i], space[j]);
            }
        }
        return -km(graph);
    }

    public static int km(int[][] graph) {
        int n = graph.length;
        int[] match = new int[n];
        int[] lx = new int[n];
        int[] ly = new int[n];
        boolean[] x = new boolean[n];
        boolean[] y = new boolean[n];
        int[] slack = new int[n];
        int invalid = Integer.MAX_VALUE;
        // 1.初始化match 和 lx ly
        for (int i = 0; i < n; i++) {
            match[i] = -1;
            lx[i] = -invalid;
            for (int j = 0; j < n; j++) {
                lx[i] = Math.max(lx[i], graph[i][j]);
            }
            ly[i] = 0;
        }

        // 2.开始从王子开始去配对了
        for (int i = 0; i < n; i++) {
            Arrays.fill(x, false);
            Arrays.fill(y, false);
            // 最开始我们的下降预期都是最大
            for (int j = 0; j < n; j++) {
                slack[j] = invalid;
            }
            while (!dfs(i, lx, ly, x, y, match, slack, graph)) {
                int d = invalid;
                for (int s = 0; s < n; s++) {
                    if (!y[s] && slack[s] < d) {
                        slack[s] = d;
                    }
                }
                for (int k = 0; k < n; k++) {
                    if (x[k]) {
                        lx[k] = lx[k] - d;
                    }
                    if (y[k]) {
                        ly[k] = ly[k] + d;
                    }
                }
                // 这里是新的一轮了，
                Arrays.fill(x, false);
                Arrays.fill(y, false);
            }
        }
        int asn = 0;
        for (int i = 0; i < n; i++) {
            asn += (lx[i] + ly[i]);
        }
        return asn;
    }

    public static boolean dfs(int from, int[] lx, int[] ly, boolean[] x, boolean[] y, int[] match, int[] slack, int[][] map) {
        int n = map.length;
        x[from] = true;
        // 去搞所有的公主
        for (int to = 0; to < n; to++) {
            int d = lx[from] + ly[to] - map[from][to];
            if (y[to] || d != 0) {
                slack[to] = Math.min(slack[to], d);
            } else {
                y[to] = true;
                if (match[to] == -1 || dfs(match[to], lx, ly, x, y, match, slack, map)) {
                    match[to] = from;
                    return true;
                }
            }
        }
        return false;
    }
}
