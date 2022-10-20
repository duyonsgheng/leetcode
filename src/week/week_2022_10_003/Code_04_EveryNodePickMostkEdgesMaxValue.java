package week.week_2022_10_003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_04_EveryNodePickMostkEdgesMaxValue
 * @Author Duys
 * @Description
 * @Date 2022/10/20 13:45
 **/

// 来自Lucid Air
// 给定一个无向图，保证所有节点连成一棵树，没有环
// 给定一个正数n为节点数，所以节点编号为0~n-1，那么就一定有n-1条边
// 每条边形式为{a, b, w}，意思是a和b之间的无向边，权值为w
// 要求：给定一个正数k，表示在挑选之后，每个点相连的边，数量都不能超过k
// 注意：是每个点的连接数量，都不超过k！不是总连接数量不能超过k！
// 你可以随意挑选边留下，剩下的边删掉，但是要满足上面的要求
// 返回不违反要求的情况下，你挑选边所能达到的最大权值累加和
public class Code_04_EveryNodePickMostkEdgesMaxValue {

    // 思路：
    // 题意给出的是一棵树
    // 树形Dp。来到一个节点，看看自己与父节点相连的情况下和不相连的情况下结果
    // 然后从如果相连就取 k-1个结果靠前的节点，不相连就取k个
    public static int[][] dp = new int[100001][2];
    public static int[] help = new int[100001];

    public int maxSum(int n, int k, int[][] edges) {
        // 建立图
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }
        dfs(graph, 0, -1, k);
        return dp[0][0];
    }

    public void dfs(List<List<int[]>> graph, int cur, int father, int k) {
        List<int[]> edges = graph.get(cur);
        for (int i = 0; i < edges.size(); i++) {
            int next = edges.get(i)[0];
            if (next != father) {
                dfs(graph, next, cur, k);
            }
        }
        int ans0 = 0;
        int ans1 = 0;
        int m = 0;
        // 算出结果
        for (int i = 0; i < edges.size(); i++) {
            int next = edges.get(i)[0];
            int weight = edges.get(i)[1];
            if (next == father) {
                continue;
            }
            ans0 += dp[next][0];
            ans1 += dp[next][0];
            if (dp[next][0] < dp[next][1] + weight) {
                help[m++] = dp[next][1] + weight - dp[next][0];
            }
        }
        Arrays.sort(help, 0, m);
        for (int i = m - 1, cnt = 1; i >= 0 && cnt <= k; i--, cnt++) {
            if (cnt <= k - 1) {
                ans0 += help[i];
                ans1 += help[i];
            }
            if (cnt == k) {
                ans0 += help[i];
            }
        }
        dp[cur][0] = ans0;
        dp[cur][1] = ans1;
    }
}
