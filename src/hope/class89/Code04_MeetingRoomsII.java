package hope.class89;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Code04_MeetingRoomsII
 * @date 2024年08月22日 下午 04:32
 */
// 会议室II
// 给你一个会议时间安排的数组 intervals
// 每个会议时间都会包括开始和结束的时间intervals[i]=[starti, endi]
// 返回所需会议室的最小数量
// 测试链接 : https://leetcode.cn/problems/meeting-rooms-ii/
public class Code04_MeetingRoomsII {
    // 贪心往往和堆也很搭配
    // 线段的最多重合问题
    public static int minMeetingRooms(int[][] meeting) {
        int n = meeting.length;
        // 根据开始时间排序
        Arrays.sort(meeting, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 如果上一个会议的结束时间小于等于了当前开始时间，说明不需要新开会议室
            while (!heap.isEmpty() && heap.peek() <= meeting[i][0]) {
                heap.poll();
            }
            heap.add(meeting[i][1]);
            ans = Math.max(ans, heap.size());
        }
        return ans;
    }
}
