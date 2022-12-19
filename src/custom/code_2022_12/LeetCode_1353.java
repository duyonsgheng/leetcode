package custom.code_2022_12;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1353
 * @Author Duys
 * @Description
 * @Date 2022/12/13 16:18
 **/
//1353. 最多可以参加的会议数目
public class LeetCode_1353 {

    public int maxEvents(int[][] events) {
        // 根据会议的开始时间排序，开始时间相同，按照小的排前面
        int n = events.length;
        Arrays.sort(events, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);// 结束时间
        int ans = 0, pre = 1, index = 0;
        while (index < n || !queue.isEmpty()) {
            // 把开始时间满足的全部加进去
            while (index < n && (events[index][0] <= pre || queue.isEmpty())) {
                queue.add(new int[]{events[index][0], events[index][1]});
                index++;
            }
            // 弹出无效的，结束时间都小于了当前时间的，无效
            while (!queue.isEmpty() && queue.peek()[1] < pre)
                queue.poll();
            if (!queue.isEmpty()) {
                ans++;
                pre = Math.max(pre, queue.poll()[0]);
                pre++;
            }
        }
        return ans;
    }
}
