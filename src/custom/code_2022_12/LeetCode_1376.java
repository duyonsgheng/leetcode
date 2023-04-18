package custom.code_2022_12;

import java.util.*;

// 1376. 通知所有员工所需的时间
public class LeetCode_1376 {
    // 建图
    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // manager 对应的下属，已经从manager到下属所需要的时间
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (manager[i] == -1) {
                continue;
            }
            if (!graph.containsKey(manager[i])) {
                graph.put(manager[i], new ArrayList<>());
            }
            graph.get(manager[i]).add(new int[]{i, informTime[manager[i]]});
        }
        // 从头结点到当前总共耗时
        Queue<int[]> queue = new LinkedList<>();
        //boolean[] visited = new boolean[n];
        queue.add(new int[]{headID, 0});
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                ans = Math.max(ans, cur[1]);
                List<int[]> nexts = graph.get(cur[0]);
                if (nexts == null || nexts.size() <= 0) {
                    continue;
                }
                for (int[] next : nexts) {
                    queue.add(new int[]{next[0], cur[1] + next[1]});
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //n = 1, headID = 0, manager = [-1], informTime = [0]
        //System.out.println(numOfMinutes(1, 0, new int[]{-1}, new int[]{0}));
        // n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
        System.out.println(numOfMinutes(6, 2, new int[]{2, 2, -1, 2, 2, 2}, new int[]{0, 0, 1, 0, 0, 0}));
    }
}
