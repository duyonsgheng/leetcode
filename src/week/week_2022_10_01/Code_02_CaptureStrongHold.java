package week.week_2022_10_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_02_CaptureStrongHold
 * @Author Duys
 * @Description
 * @Date 2022/10/8 11:25
 **/
// 来自Leetcode周赛
// 魔物了占领若干据点，这些据点被若干条道路相连接，
// roads[i] = [x, y] 表示编号 x、y 的两个据点通过一条道路连接。
// 现在勇者要将按照以下原则将这些据点逐一夺回：
// 在开始的时候，勇者可以花费资源先夺回一些据点，
// 初始夺回第 j 个据点所需消耗的资源数量为 cost[j]
// 接下来，勇者在不消耗资源情况下，
// 每次可以夺回一个和「已夺回据点」相连接的魔物据点，
// 并对其进行夺回
// 为了防止魔物暴动，勇者在每一次夺回据点后（包括花费资源夺回据点后），
// 需要保证剩余的所有魔物据点之间是相连通的（不经过「已夺回据点」）。
// 请返回勇者夺回所有据点需要消耗的最少资源数量。
// 输入保证初始所有据点都是连通的，且不存在重边和自环
// 测试链接 : https://leetcode.cn/problems/s5kipK/
public class Code_02_CaptureStrongHold {

    // 分析
    // 本题考的是切点，通过强联通分量获得的口袋，如果一个口袋只有一个切点，那么我们就医考虑从当前环切入
    // 通过画图分析，先找到只有一个切点的哪些叶子口袋 n个 ，那么再从这些叶子口袋中选择 n-1个口袋，从这n-1个口袋中选择n-1的值较小的点出发

    public long minimumCost(int[] cost, int[][] roads) {
        int n = cost.length;
        if (n == 1) {
            return cost[0];
        }
        int m = roads.length;
        DoubleConnectedComponents dcc = new DoubleConnectedComponents(n, m, roads);
        long ans = 0;
        if (dcc.dcc.size() == 1) {
            ans = Integer.MAX_VALUE;
            for (int num : cost) {
                ans = Math.min(ans, num);
            }
        } else {
            List<Integer> arr = new ArrayList<>();
            for (List<Integer> set : dcc.dcc) {
                int cutCnt = 0;
                int cutCost = Integer.MAX_VALUE;
                for (int nodes : set) {
                    if (dcc.cut[nodes]) {
                        cutCnt++;
                    } else {
                        cutCost = Math.min(cutCost, cost[nodes]);
                    }
                }
                if (cutCnt == 1) {
                    arr.add(cutCost);
                }
            }
            arr.sort((a, b) -> a - b);
            for (int i = 0; i < arr.size() - 1; i++) {
                ans += arr.get(i);
            }
        }
        return ans;
    }

    // 双联通分量
    public class DoubleConnectedComponents {
        /*-----链式前向星的图表示方式-----*/
        public int[] head;
        public int[] next;
        public int[] to;
        /*--------------------------*/
        public int[] dfn;
        public int[] low;
        public int[] stack;
        public List<List<Integer>> dcc;
        public boolean[] cut;
        public int edgeCnt;
        public int dfnCnt;
        public int top;
        public int root;

        public DoubleConnectedComponents(int n, int m, int[][] roads) {
            init(n, m);
            createGraph(roads);
            createDcc(n);
        }

        private void init(int n, int m) {
            head = new int[n];
            Arrays.fill(head, -1);
            next = new int[m << 1];
            to = new int[m << 1];
            dfn = new int[n];
            low = new int[n];
            stack = new int[n];
            dcc = new ArrayList<>();
            cut = new boolean[n];
            edgeCnt = 0;
            dfnCnt = 0;
            top = 0;
            root = 0;
        }

        private void createGraph(int[][] roads) {
            for (int[] edges : roads) {
                add(edges[0], edges[1]);
                add(edges[1], edges[0]);
            }
        }

        private void add(int u, int v) {
            to[edgeCnt] = v;
            next[edgeCnt] = head[u];
            head[u] = edgeCnt++;
        }

        private void createDcc(int n) {
            for (int i = 0; i < n; i++) {
                if (dfn[i] == 0) {
                    root = i;
                    tarjan(i);
                }
            }
        }

        private void tarjan(int x) {
            dfn[x] = low[x] = ++dfnCnt;
            stack[top++] = x;
            int flag = 0;
            if (x == root && head[x] == -1) {
                dcc.add(new ArrayList<>());
                dcc.get(dcc.size() - 1).add(x);
                return;
            }
            // 当前来到x节点
            for (int i = head[x]; i >= 0; i = next[i]) {
                int y = to[i];// y是下级节点
                if (dfn[y] == 0) { // y点没来过
                    tarjan(y);
                    // 当前环几个切点
                    if (low[y] >= dfn[x]) {
                        flag++;
                        if (x != root || flag > 1) {
                            cut[x] = true;
                        }
                        List<Integer> cur = new ArrayList<>();
                        // 从栈弹出节点， 但是不弹切点
                        for (int z = stack[--top]; z != y; z = stack[--top]) {
                            cur.add(z);
                        }
                        cur.add(y);
                        cur.add(x);
                        dcc.add(cur);
                    }
                    low[x] = Math.min(low[x], low[y]);
                } else {
                    low[x] = Math.min(low[x], dfn[y]);
                }
            }
        }
    }
}
