package week.week_2022_12_01;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_03_LeetCode_834
 * @Author Duys
 * @Description
 * @Date 2022/12/8 10:12
 **/
// 834. 树中距离之和
public class Code_03_LeetCode_834 {
    // 建图。
    // 然后选择一个店作为顶点，算所有的点到顶点的距离是多少
    // 然后来做信息收集，再来根据收集的信息得出答案
    int max = 30001;
    int[] size = new int[max];
    int[] dist = new int[max];

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        buildInfo(0, -1, graph);
        int[] ans = new int[n];
        buildAns(0, -1, 0, graph, ans);
        return ans;
    }

    // 当前节点 cur
    // 当前节点的父节点 father
    // 父节点到cur的距离之和 preDist
    public void buildAns(int cur, int father, int preDist, List<List<Integer>> graph, int[] ans) {
        ans[cur] = dist[cur] + preDist; // 分为两部分，第一部分是左右的孩子节点到cur，第二部分是从父节点到cur
        for (int next : graph.get(cur)) {
            if (next == father) {
                continue;
            }
            // 两部分
            // 第一部分 很好算，直接从dist种拿
            // 第二部分，需要计算，所有的节点到父节点算出来了。
            // 父节点的答案，减去 next的。就是除了next这一个子树 ，其他的节点到父节点的距离。 就是ans[cur] - dist[next] - size[next]
            // 然后有多少个孩子节点 size[0] - size[next]
            buildAns(next, cur, ans[cur] - dist[next] + size[0] - (size[next] << 1), graph, ans);
        }
    }

    public void buildInfo(int cur, int father, List<List<Integer>> graph) {
        size[cur] = 1;
        dist[cur] = 0;
        for (int next : graph.get(cur)) {
            if (next == father) {
                continue;
            }
            buildInfo(next, cur, graph);
            // 所有的节点到我，需要+之前的节点到next，然后从next到cur
            dist[cur] += dist[next] + size[next];
            size[cur] += size[next];
        }
    }
}
