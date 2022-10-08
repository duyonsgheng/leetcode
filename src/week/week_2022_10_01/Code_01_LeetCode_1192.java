package week.week_2022_10_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_01_LeetCode_1192
 * @Author Duys
 * @Description
 * @Date 2022/10/8 9:22
 **/
// 1192. 查找集群内的「关键连接」
public class Code_01_LeetCode_1192 {
    // 分析
    // 强联通分量可以找到哪些是口袋，通过父级节点来到口袋节点的边就是桥
    // 所以通过tarjan 可以找到口袋
    // 通过口袋。来找到 桥
    public int[] dfn = new int[100010]; // 每个节点对应的dfn序号
    public int[] low = new int[100010]; // 每个节点的头，就是能不能扎起口袋
    public int cnt = 0; // 全局的cnt

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // 1.建图
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> point : connections) {
            graph.get(point.get(0)).add(point.get(1));
            graph.get(point.get(1)).add(point.get(0));
        }
        Arrays.fill(dfn, 0, n, 0);
        Arrays.fill(low, 0, n, 0);
        cnt = 0;
        // 跑tarjan
        List<List<Integer>> ans = new ArrayList<>();
        tarjan(0, -1, graph, ans);
        return ans;
    }

    public void tarjan(int cur, int father, List<List<Integer>> graph, List<List<Integer>> ans) {
        dfn[cur] = low[cur] = ++cnt;
        for (Integer next : graph.get(cur)) {
            if (next == father) {
                continue;
            }
            // 没来过
            if (dfn[next] == 0) {
                // 继续
                tarjan(next, cur, graph, ans);
                // 抓一个low，看看口袋扎那儿了
                low[cur] = Math.min(low[cur], low[next]);
            }
            // 可能公用的点
            else {
                low[cur] = Math.min(low[cur], dfn[next]);
            }
        }
        // cur != 0的意思是，头节点不存在有桥的情况
        if (low[cur] == dfn[cur] && cur != 0) {
            ans.add(Arrays.asList(father, cur));
        }
    }
}
