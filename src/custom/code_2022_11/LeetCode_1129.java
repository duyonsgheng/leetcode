package custom.code_2022_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_1129
 * @Author Duys
 * @Description
 * @Date 2022/11/15 15:13
 **/
// 1129. 颜色交替的最短路径
public class LeetCode_1129 {
    // 经典的缔结特斯拉
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // 转为邻接表
        List<List<Integer>> readList = new ArrayList<>();
        List<List<Integer>> blueList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            readList.add(new ArrayList<>());
            blueList.add(new ArrayList<>());
        }
        for (int[] read : redEdges) {
            readList.get(read[0]).add(read[1]);
        }
        for (int[] blue : blueEdges) {
            blueList.get(blue[0]).add(blue[1]);
        }
        boolean[][] visited = new boolean[n][2];

        // int[] [点,颜色,路径]
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});
        queue.add(new int[]{0, 1, 0});
        visited[0][0] = true;
        visited[0][1] = true;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int pot = cur[0];
            int color = cur[1];
            int cost = cur[2];
            if (ans[pot] == -1) {
                ans[pot] = cost;
            }
            // 红色，下一步走蓝色
            if (color == 0) {
                for (int next : blueList.get(pot)) {
                    if (visited[next][1]) {
                        continue;
                    }
                    visited[next][1] = true;
                    queue.add(new int[]{next, 1, cost + 1});
                }
            } else {
                for (int next : readList.get(pot)) {
                    if (visited[next][0]) {
                        continue;
                    }
                    visited[next][0] = true;
                    queue.add(new int[]{next, 0, cost + 1});
                }
            }
        }
        return ans;
    }
}
