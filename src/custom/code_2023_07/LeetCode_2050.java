package custom.code_2023_07;

import java.util.*;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2050
 * @date 2023年07月28日
 */
// 2050. 并行课程 III
// https://leetcode.cn/problems/parallel-courses-iii/
public class LeetCode_2050 {
    // 拓扑排序
    public static int minimumTime(int n, int[][] relations, int[] time) {
        int[] in = new int[n + 1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < relations.length; i++) {
            in[relations[i][1] - 1]++;
            // 1的后续是 2，当前的时间是 0
            graph.get(relations[i][0] - 1).add(relations[i][1] - 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                queue.add(new int[]{time[i], i});
            }
        }
        int curTime = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            curTime = cur[0];
            List<Integer> nexts = graph.get(cur[1]);
            for (int next : nexts) {
                if (--in[next] == 0) {
                    queue.add(new int[]{curTime + time[next], next});
                }
            }
        }
        return curTime;
    }

    public static void main(String[] args) {
        // n = 5, relations = [[1,5],[2,5],[3,5],[3,4],[4,5]], time = [1,2,3,4,5]
        System.out.println(minimumTime(5, new int[][]{{1, 5}, {2, 5}, {3, 5}, {3, 4}, {4, 5}}, new int[]{1, 2, 3, 4, 5}));
        // n = 3, relations = [[1,3],[2,3]], time = [3,2,5]
        System.out.println(minimumTime(3, new int[][]{{1, 3}, {2, 3}}, new int[]{3, 2, 5}));
        System.out.println(minimumTime(2, new int[][]{}, new int[]{3, 5}));
    }
}
