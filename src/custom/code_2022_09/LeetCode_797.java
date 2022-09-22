package custom.code_2022_09;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName LeetCode_797
 * @Author Duys
 * @Description
 * @Date 2022/9/21 13:36
 **/
// 797. 所有可能的路径
public class LeetCode_797 {

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        if (graph == null || graph.length <= 0 || graph[0] == null || graph[0].length <= 0) {
            return ans;
        }
        int tar = graph.length - 1;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                int curPot = cur.node;
                if (curPot == tar) {
                    cur.from.add(curPot);
                    ans.add(cur.from);
                    continue;
                }
                int[] nexts = graph[curPot];
                List<Integer> from = cur.from;
                if (nexts == null || nexts.length <= 0) {
                    continue;
                }
                for (int next : nexts) {
                    if (from.contains(next)) {
                        continue;
                    }
                    Node nextN = new Node(next);
                    nextN.from.addAll(from);
                    nextN.from.add(curPot);
                    queue.offer(nextN);
                }
            }
        }
        return ans;
    }

    static class Node {
        int node;
        List<Integer> from;

        public Node(int n) {
            node = n;
            from = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        int[][] gp = new int[][]{{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        List<List<Integer>> list = allPathsSourceTarget(gp);
        System.out.println();
    }
}
