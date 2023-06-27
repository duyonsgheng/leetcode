package custom.code_2023_05;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1834
 * @Author Duys
 * @Description
 * @Date 2023/5/26 13:21
 **/
// 1834. 单线程 CPU
public class LeetCode_1834 {

    // tasks = [[1,2],[2,4],[3,2],[4,1]]
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[] ans = new int[n];
        int[][] end = new int[n][3];
        for (int i = 0; i < n; i++) {
            end[i][0] = tasks[i][0];
            end[i][1] = tasks[i][1];
            end[i][2] = i;
        }
        Arrays.sort(end, (a, b) -> a[0] - b[0]);
        // 先时间，在编号
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
        for (int time = 1, j = 0, i = 0; i < n; ) {
            // 如果当前的任务可以入队列
            while (j < n && end[j][0] <= time) {
                queue.add(end[j++]);
            }
            // 如果之前的任务都执行完了，。
            if (queue.isEmpty()) {
                // 更新下一次任务的开始时间
                time = end[j][0];
            } else {
                int[] cur = queue.poll();
                ans[i++] = cur[2];
                // 更新下一次任务的开始时间
                time += cur[1];
            }
        }
        return ans;
    }
}
