package custom.code_2023_09;

import java.util.*;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2192
 * @date 2023年09月08日
 */
// 2192. 有向无环图中一个节点的所有祖先
// https://leetcode.cn/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/
public class LeetCode_2192 {
    // 普通的拓扑排序
    public static List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        List<TreeSet<Integer>> demo = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            ans.add(new ArrayList<>());
            demo.add(new TreeSet<>());
        }
        // 有向图
        int[] in = new int[n];
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            in[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                List<Integer> list = graph.get(cur);
                for (int next : list) {
                    in[next]--;
                    demo.get(next).add(cur);
                    demo.get(next).addAll(demo.get(cur));
                    if (in[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            ans.get(i).addAll(demo.get(i));
        }
        return ans;
    }


    public static void main(String[] args) {
        // n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
        List<List<Integer>> ancestors = getAncestors(8, new int[][]{{0, 3}, {0, 4}, {1, 3}, {2, 4}, {2, 7}, {3, 5}, {3, 6}, {3, 7}, {4, 6}});
        System.out.println();
    }
}
