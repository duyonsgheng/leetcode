package week.week_2022_03_01;

import java.util.ArrayList;

/**
 * @ClassName Code_01_StronglyConnectedComponents
 * @Author Duys
 * @Description
 * @Date 2022/3/23 15:01
 **/
public class Code_01_StronglyConnectedComponents {
    // 名词解释：
    // dfn 数组 在一个有向图中，先把节点按照dfn序编号，深度优先遍历的时候给编号
    // low 从当前节点出发，往后所有的子节点能回到最小的dfn编号
    // 算法流程：
    // 1.父节点的dfn一定比子节点的小
    // 2.从当前节点出发往后dfs，然后依次回退的时候，判断low是否需要更新
    // 3.每一个节点有状态：未遍历，遍历未结算，遍历已结算使用stack来标记
    // 4.节点往下dfs的时候，不要去碰，遍历已结算的节点。
    // 5.当来到一个节点，压栈，直到节点遍历完，然后开始设置low，当dfn和low相等，该结算了，从栈里弹，弹到当前dfn=low的节点，结算弹出的那一批是一个强连通份量。

    // tarjan算法求有向图的强连通分量

    public static class StronglyConnectedComponents {
        public ArrayList<ArrayList<Integer>> nexts;
        public int n;
        public int[] stack;
        public int stackSize;
        public int[] dfn;
        public int[] low;
        public int cnt;
        public int[] scc;
        public int sccn;

        // 根据图来进行连线
        // 为了避免发生歧义
        // edges里面是从0开始，在tarjan里面从1开始
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
            for (int i = 0; i <= n; i++) {
                if (dfn[i] == 0) {
                    tarjan(i);
                }
            }
        }

        private void tarjan(int p) {
            // 刚刚开始遍历，low = dfn
            low[p] = dfn[p] = ++cnt;
            // 入栈
            stack[stackSize++] = p;
            // 当前p的所有后代跑dfs
            for (int q : nexts.get(p)) {
                // q是当前p的每一个孩子
                if (dfn[q] == 0) {
                    // 如果当前孩子还没算过，你去算去
                    tarjan(q);
                }
                // 到这儿了，说明q一定遍历了，但是有两种情况，1.遍历了，没结算，2.遍历了，也结算了
                // scc[q] == 0 说明q还没有跟某一个集团打成一片
                if (scc[q] == 0) {
                    low[p] = Math.min(low[p], low[q]);
                }
            }
            //  当所有的子节点都遍历完了，才结算
            // 如果属于同一个集团了，更新其他的参数
            if (low[p] == dfn[p]) {
                sccn++;
                int top = 0;
                do {
                    top = stack[stackSize--];
                    scc[top] = sccn;
                } while (top != p);
            }
        }

        // 生成有向无环缩点图
        public ArrayList<ArrayList<Integer>> getShortGraph() {
            ArrayList<ArrayList<Integer>> shortGraph = new ArrayList();
            for (int i = 0; i <= sccn; i++) {
                shortGraph.add(new ArrayList<>());
            }
            for (int i = 0; i <= n; i++) {
                for (int v : nexts.get(i)) {
                    if (scc[i] != scc[v]) {
                        shortGraph.get(scc[i]).add(scc[v]);
                    }
                }
            }
            return shortGraph;
        }
    }
}
