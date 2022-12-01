package week.week_2022_11_05;

import java.util.PriorityQueue;

/**
 * @ClassName Code_01_FinishOrdersEndTimes
 * @Author Duys
 * @Description
 * @Date 2022/12/1 9:32
 **/
// 给定一个数组componets，长度为A，
// componets[i] = j，代表i类型的任务需要耗时j
// 给定一个二维数组orders，长度为M，
// orders[i][0]代表i号订单下单时间
// orders[i][1]代表i号订单是哪种类型的任务，毫无疑问orders[i][1] < A
// 一开始所有流水线都在0时刻待命，
// 给定一个正数nums，表示流水线的数量，流水线编号为0 ~ nums-1
// 每一个流水线可以承接任何类型的任务，耗时就是componets数组给定的
// 所有订单的下单时间一定是有序的，也就是orders数组，是根据下单时间排序的
// 每一个订单开始执行的时间不能早于下单时间，
// 如果有多个流水线都可以执行当前订单，选择编号最小的流水线
// 根据上面说的任务执行细节，去依次完成所有订单
// 返回长度为M的数组ans，也就是和orders等长
// ans[i][0]代表i号订单是由哪条流水线执行的
// ans[i][1]代表i号订单的完成时间
// 1 <= A <= 10^5
// 1 <= M <= 10^5
// 1 <= nums <= 10^5
// 1 <= 时间数值 <= 10^5
public class Code_01_FinishOrdersEndTimes {
    // 用两个堆来存放流水线
    // 一个空闲堆，用编号排序，编号越小的放在最前面
    // 一个使用堆，按照醒来的时间，如果时间一样，那么按照编号排序

    public static int[][] times(int nums, int[] componets, int[][] orders) {
        int n = orders.length;
        // 工作堆
        PriorityQueue<Line> sleepLines = new PriorityQueue<>((a, b) -> (a.time != b.time ? a.time - b.time : a.index - b.index));
        // 空闲堆
        PriorityQueue<Line> canUseLines = new PriorityQueue<>((a, b) -> a.index - b.index);
        // 初始化空闲堆
        for (int i = 0; i < nums; i++) {
            canUseLines.add(new Line(0, i));
        }
        int[][] ans = new int[n][2];
        for (int i = 0; i < n; i++) {
            int startTime = orders[i][0];
            int jobType = orders[i][1];
            // 唤醒工作堆里面所有可用的流水线
            while (!sleepLines.isEmpty() && sleepLines.peek().time <= startTime) {
                canUseLines.add(sleepLines.poll());
            }
            // 现在去找对应的流水线了
            Line cur = null;
            if (canUseLines.isEmpty()) {
                cur = sleepLines.poll();
                ans[i][1] = cur.time + componets[jobType];
            } else {
                // 从空闲种拿出来的，默认空闲的开始时间都是0.
                cur = canUseLines.poll();
                ans[i][1] = startTime + componets[jobType];
            }
            ans[i][0] = cur.index;
            cur.time = ans[i][1];
            sleepLines.add(cur);
        }
        return ans;
    }

    static class Line {
        int time;
        int index;

        public Line(int t, int i) {
            time = t;
            index = i;
        }
    }
}
