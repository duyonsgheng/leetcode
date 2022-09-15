package week.week_2022_03_05;

/**
 * @ClassName Code_01_KMAlgorithm
 * @Author Duys
 * @Description
 * @Date 2022/3/31 10:00
 **/

import java.util.Arrays;

/**
 * KM 算法
 */
public class Code_01_KMAlgorithm {

    // 题意：
    // 在图中 g[i][j] 表示 i配对j能得到的好处
    // 问给出一个二维数组，要求每一个行和一个列配置，返回最佳的好处
    // 暴力解答
    public static int right(int[][] graph) {
        int n = graph.length;
        int[] to = new int[n];
        for (int i = 0; i < n; i++) {
            to[i] = 1;
        }
        return process(0, to, graph);
    }

    public static int process(int from, int[] to, int[][] graph) {
        if (from == graph.length) {
            return 0;
        }
        int ans = 0;
        // 尝试当前的from 去 配每一个to
        for (int i = 0; i < to.length; i++) {
            if (to[i] == 1) {
                to[i] = 0;
                ans = Math.max(ans, graph[from][i] + process(from + 1, to, graph));
                to[i] = 1;
            }
        }
        return ans;
    }


    // km 算法原型
    public static int km(int[][] graph) {
        int n = graph.length;
        // match是当前公主是谁与之配对的
        int[] match = new int[n];

        // 王子最好的预期
        int[] lx = new int[n];
        // 公主最好的预期
        int[] ly = new int[n];

        // 当前王子有没有碰过
        boolean[] bx = new boolean[n];
        // 当前公主有没有碰过
        boolean[] by = new boolean[n];

        // 降低预期最小的
        int[] slack = new int[n];

        int invalid = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            match[i] = -1;
            lx[i] = -invalid;
            // 更新王子最好的期望
            for (int j = 0; j < n; j++) {
                lx[i] = Math.max(lx[i], graph[i][j]);
            }
            // 公主的初始期望全部是0
            ly[i] = 0;
        }
        // 每个王子出发，去配最合适的
        for (int f = 0; f < n; f++) {
            // 每次尝试
            for (int i = 0; i < n; i++) {
                slack[i] = invalid;
            }
            Arrays.fill(bx, false);
            Arrays.fill(by, false);
            // 如果当前没成功，需要降低期望，然后去尝试了
            while (!dfs(f, bx, by, lx, ly, match, slack, graph)) {
                int d = invalid;
                // 如果没有碰过，那么去拿最小的需要降低的期望
                for (int i = 0; i < n; i++) {
                    d = !by[i] ? Math.min(slack[i], d) : d;
                }

                for (int i = 0; i < n; i++) {
                    if (bx[i]) { // 已经碰过了的王子，需要降低期望，注意当前正在尝试的这一个王子也算碰过了，因为在dfs里面会修改
                        lx[i] = lx[i] - d;
                    }
                    if (by[i]) { // 已经碰过了的公主，需要增加期望
                        ly[i] = ly[i] + d;
                    }
                }
                // 这里是新的一轮了，
                Arrays.fill(bx, false);
                Arrays.fill(by, false);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (lx[i] + ly[i]);
        }
        return ans;
    }

    public static boolean dfs(int from, boolean[] x, boolean[] y, int[] lx, int[] ly, int[] match, int[] slack, int[][] map) {
        int n = map.length;
        x[from] = true;// 当前王子算碰过了
        // 当前王子去尝试每一个公主
        for (int to = 0; to < n; to++) {
            // 差值
            int d = lx[from] + ly[to] - map[from][to];
            // 这里，如果在这一次交换中，之前有一次来尝试过了to，并且没成功，那么不用尝试， 或者我们的d不=0，说明不合适
            if (y[to] || d != 0) {
                slack[to] = Math.min(slack[to], d);
            } else {
                y[to] = true;
                // 如果当前to的之前配对王子是-1，说明没有，如果当前to公主，之前有与之配对的，那么断掉去跑dfs，重新生成关系
                if (match[to] == -1 || dfs(match[to], x, y, lx, ly, match, slack, map)) {
                    match[to] = from;
                    return true;
                }
            }
        }
        return false;

    }

}
