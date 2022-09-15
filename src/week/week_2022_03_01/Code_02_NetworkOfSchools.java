package week.week_2022_03_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_02_NetworkOfSchools
 * @Author Duys
 * @Description
 * @Date 2022/8/18 11:27
 **/
// 强联通分量练习题1
// N个学校之间有单向的网络，每个学校得到一套软件后，可以通过单向网络向周边的学校传输
// 问题1：初始至少需要向多少个学校发放软件，使得网络内所有的学校最终都能得到软件
// 问题2：至少需要添加几条传输线路(边)，使任意向一个学校发放软件后
// 经过若干次传送，网络内所有的学校最终都能得到软件
// 2 <= N <= 1000
// 从题意中抽象出的算法模型, 给定一个有向图，求：
// 1) 至少要选几个顶点，才能做到从这些顶点出发，可以到达全部顶点
// 2) 至少要加多少条边，才能使得从任何一个顶点出发，都能到达全部顶点
// 测试链接 : http://poj.org/problem?id=1236
// 注册一下 -> 页面上点击"submit" -> 语言选择java
// 然后把如下代码粘贴进去, 把主类名改成"Main", 可以直接通过
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
public class Code_02_NetworkOfSchools {
    public static void main(String[] args) throws Exception {
        // 根据输入建立图
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval; // 有多少个点
            ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i <= n; i++) {
                edges.add(new ArrayList<Integer>());
            }
            for (int from = 1; from <= n; from++) {
                do {
                    in.nextToken();
                    int to = (int) in.nval;
                    if (to == 0) {
                        break;
                    } else {
                        edges.get(from).add(to);
                    }
                } while (true);
            }
            StronglyConnectedComponents stronglyConnectedComponents = new StronglyConnectedComponents(edges);
            int sccn = stronglyConnectedComponents.getSccn();
            int[] ins = new int[sccn + 1]; // 每个点的入度
            int[] outs = new int[sccn + 1]; // 每个点的出度
            ArrayList<ArrayList<Integer>> shortGraph = stronglyConnectedComponents.getShortGraph();
            for (int dot = 1; dot <= sccn; dot++) {
                for (int next : shortGraph.get(dot)) {
                    outs[dot]++;
                    ins[next]++;
                }
            }
            // 入度为0的点几个
            int inZero = 0;
            // 出度为0的点几个
            int outZero = 0;
            for (int i = 1; i <= sccn; i++) {
                if (ins[i] == 0) {
                    inZero++;
                }
                if (outs[i] == 0) {
                    outZero++;
                }
            }
            // 第一问
            out.println(inZero);
            // 第二问
            out.println(sccn == 1 ? 0 : Math.max(inZero, outZero));
            out.flush();
        }
    }

    // 创建强联通分量
    public static class StronglyConnectedComponents {
        // 图
        public ArrayList<ArrayList<Integer>> nexts;
        // 多少点
        public int n;

        public int[] stack; // 遍历的过程中方便结算
        public int stackSize;

        public int[] dfn;
        public int[] low;
        public int cnt;


        public int[] scc;
        public int sccn;

        public StronglyConnectedComponents(ArrayList<ArrayList<Integer>> edges) {
            nexts = edges;
            init();
            scc();
        }

        private void init() {
            n = nexts.size();
            stack = new int[n];
            stackSize = 0;
            dfn = new int[n];
            low = new int[n];
            cnt = 0;
            scc = new int[n];
            sccn = 0;
            n--;
        }

        private void scc() {
            for (int i = 1; i <= n; i++) {
                // 当前点没有遍历过
                if (dfn[i] == 0) {
                    tarjan(i);
                }
            }
        }

        private void tarjan(int from) {
            // 没遍历过，也没结算过
            dfn[from] = low[from] = ++cnt;
            // 入栈
            stack[stackSize++] = from;
            // dfs遍历去
            for (int next : nexts.get(from)) {
                // 没遍历过，才去遍历
                // 跑一个dfs
                if (dfn[next] == 0) {
                    tarjan(next);
                }
                // 当前节点遍历结束了
                // 看看当前节点是否已经属于某一个集团了，也就是是否属于某一个口袋(强连通分量)
                // 如果属于了某一个集团了，也就是说已经被遍历且被结算了，就不需要再去碰这个点了
                if (scc[next] == 0) {
                    low[from] = Math.min(low[next], low[from]);
                }
            }
            // 当前from节点的所有孩子都遍历结束了
            // 检查并且设置同一个集团
            // 如果low和dfn相同了，说明从自己出发经过一个dfs又转回来了，形成了一个环
            if (low[from] == dfn[from]) {
                sccn++;
                int top = 0;
                do {
                    // 弹出本次压栈的所有点
                    top = stack[--stackSize];
                    // 这一批次弹出的点，属于同一个集团
                    scc[top] = sccn;
                } while (top != from);
            }
        }

        public int[] getScc() {
            return scc;
        }

        public int getSccn() {
            return sccn;
        }

        // 建立一个有向无环缩点图
        public ArrayList<ArrayList<Integer>> getShortGraph() {
            //
            ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i <= sccn; i++) {
                graph.add(new ArrayList<Integer>());
            }
            for (int dot = 1; dot <= n; dot++) {
                for (int next : nexts.get(dot)) {
                    // 如果不属于同一个集团，说明两个集团有边可以链接
                    if (scc[dot] != scc[next]) {
                        graph.get(scc[dot]).add(scc[next]);
                    }
                }
            }
            return graph;
        }
    }
}
