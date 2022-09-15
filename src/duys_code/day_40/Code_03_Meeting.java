package duys_code.day_40;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName Code_03_Metting
 * @Author Duys
 * @Description
 * @Date 2021/12/22 11:26
 **/
public class Code_03_Meeting {

    // 给定int[][] meetings，比如
    // {
    //   {66, 70}   0号会议截止时间66，获得收益70
    //   {25, 90}   1号会议截止时间25，获得收益90
    //   {50, 30}   2号会议截止时间50，获得收益30
    // }
    // 一开始的时间是0，任何会议都持续10的时间，但是一个会议一定要在该会议截止时间之前开始
    // 只有一个会议室，任何会议不能共用会议室，一旦一个会议被正确安排，将获得这个会议的收益
    // 请返回最大的收益

    // 小根堆来解决这个问题
    // 1.先按照我们的截止时间来排序
    // 2.然后依次加入会议，如果截止时间在当前的结束时间之前的，可以加入，否则看看能不能踢掉之前的会议收益。
    public static int maxScore(int[][] m) {
        // 根据截止时间排序
        Arrays.sort(m, (a, b) -> a[0] - b[0]);
        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int endTime = 0;
        for (int i = 0; i < m.length; i++) {
            // 如果当前的会议时间是大于等于上一轮会议的结束时间
            if (endTime + 10 <= m[i][0]) {
                heap.add(m[i][1]);
                endTime += 10;
            } else {
                // 如果我的上一轮会议是比我当前收益要小的
                if (!heap.isEmpty() && heap.peek() < m[i][1]) {
                    heap.poll();
                    heap.add(m[i][1]);
                }
            }
        }
        int ans = 0;
        while (!heap.isEmpty()) {
            ans += heap.poll();
        }
        return ans;
    }
}
