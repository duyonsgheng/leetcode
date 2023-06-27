package custom.code_2023_06;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ClassName LeetCode_1882
 * @Author Duys
 * @Description
 * @Date 2023/6/13 10:34
 **/
// 1882. 使用服务器处理任务
public class LeetCode_1882 {
    // 双队列
    public static int[] assignTasks(int[] servers, int[] tasks) {
        // 看权重
        // 空闲
        PriorityQueue<Integer> queue1 = new PriorityQueue<>((a, b) -> servers[a] == servers[b] ? a - b : servers[a] - servers[b]);
        // 看时间
        // 工作
        PriorityQueue<int[]> queue2 = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < servers.length; i++) {
            queue1.offer(i);
        }
        int[] ans = new int[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            // 看看当前时间
            while (!queue2.isEmpty() && queue2.peek()[0] <= i) {
                queue1.offer(queue2.poll()[1]);
            }
            queue.add(i);
            // 有空闲的，并且也有任务
            while (!queue1.isEmpty() && !queue.isEmpty()) {
                int cur = queue.poll();
                int server = queue1.poll();
                ans[cur] = server;
                // 下一次空闲的时间和服务器id
                queue2.offer(new int[]{tasks[cur] + i, server});
            }
        }
        // 如果任务还没做完
        while (!queue.isEmpty()) {
            int lastTime = queue2.peek()[0]; // 看看当前最小完成任务的时间
            // 如果可以做任务了，有空闲了
            while (!queue2.isEmpty() && queue2.peek()[0] == lastTime) {
                queue1.offer(queue2.poll()[1]);
            }
            // 空闲队列
            while (!queue.isEmpty() && !queue1.isEmpty()) {
                int cur = queue.poll();
                int server = queue1.poll();
                ans[cur] = server;
                // 下一次空闲的时间和服务器id
                queue2.offer(new int[]{tasks[cur] + lastTime, server});
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ints = assignTasks(new int[]{3, 3, 2}, new int[]{1, 2, 3, 2, 1, 2});
        for (int i : ints) {
            System.out.print(i + " ");
        }
        System.out.println();
        ints = assignTasks(new int[]{5, 1, 4, 3, 2}, new int[]{2, 1, 2, 4, 5, 2, 1});
        for (int i : ints) {
            System.out.print(i + " ");
        }
    }
}
