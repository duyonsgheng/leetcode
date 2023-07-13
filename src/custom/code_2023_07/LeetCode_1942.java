package custom.code_2023_07;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1942
 * @date 2023年07月10日
 */
// 1942. 最小未被占据椅子的编号
// https://leetcode.cn/problems/the-number-of-the-smallest-unoccupied-chair/
public class LeetCode_1942 {

    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        Integer[] indexs = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexs[i] = i;
        }
        // 来的时间排序
        Arrays.sort(indexs, (a, b) -> times[a][0] - times[b][0]);
        // 当前占用的椅子，以及离开的时间。按照离开时间排序
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        // 椅子的空闲情况
        PriorityQueue<Integer> wait = new PriorityQueue<>();
        for (int i = 0; i < n; i++)
            wait.add(i);
        for (int cur : indexs) {
            // 如果人走了，则释放掉
            while (!queue.isEmpty() && queue.peek()[1] <= times[cur][0]) {
                wait.add(queue.poll()[0]);
            }
            if (cur == targetFriend) {
                return wait.peek();
            }
            // 空闲的椅子编号，最小的，以及这张椅子什么时候释放
            queue.add(new int[]{wait.poll(), times[cur][1]});
        }
        return 0;
    }
}
