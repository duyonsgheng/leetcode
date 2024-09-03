package hope.class90;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Code04_MeetingOneDay
 * @date 2024年08月26日 下午 05:19
 */
// 会议只占一天的最大会议数量
// 给定若干会议的开始、结束时间
// 任何会议的召开期间，你只需要抽出1天来参加
// 但是你安排的那一天，只能参加这个会议，不能同时参加其他会议
// 返回你能参加的最大会议数量
// 测试链接 : https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended/
public class Code04_MeetingOneDay {
    // 根据会议的开始时间排序
    // 按照天数来，然后用小根堆来记录结束时间的任务，选择结束时间小的会议开
    public static int maxEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int min = events[0][0];
        int max = events[0][1];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, events[i][1]);
        }
        // 小根堆，记录结束时间
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int ans = 0;
        for (int day = min, i = 0; day <= max; day++) {
            // 1.把当前天的会议解锁
            while (i < n && events[i][0] == day) {
                heap.add(events[i++][1]);
            }
            // 2.清理过期的任务
            while (!heap.isEmpty() && heap.peek() < day) {
                heap.poll();
            }
            // 3.选择结束时间小的会议
            if (!heap.isEmpty()) {
                heap.poll();
                ans++;
            }
        }
        return ans;
    }
}
