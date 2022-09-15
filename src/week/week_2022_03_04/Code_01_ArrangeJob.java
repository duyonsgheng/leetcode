package week.week_2022_03_04;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName Code_01_Arrange
 * @Author Duys
 * @Description
 * @Date 2022/3/24 14:12
 **/
public class Code_01_ArrangeJob {
// 来自华为
// 给定一个n*2的二维数组，表示有n个任务
// 一个信息是任务能够开始做的时间，另一个信息是任务的结束期限，后者一定大于前者，且数值上都是正数
// 你作为单线程的人，不能并行处理任务，但是每个任务都只需要一个单位时间完成
// 你需要将所有任务的执行时间，位于开始做的时间和最后期限之间
// 返回你能否做到这一点

    /**
     * 思路：
     * 1.我们把所有任务的开始时间和结束时间想象成一个时间轴
     * 2.每一组任务封装成一个小对象
     * 3.然后使用小根堆
     * 4.当遇到任务结束的时候结算看看能完成几个任务
     */
    public static boolean canDo(int[][] jobs) {
        if (jobs == null || jobs.length < 2) {
            return true;
        }
        int n = jobs.length;// 总共多少组任务
        // 任务开始和结束 是两个对象，因为在结束的时候批次结算
        TimePoint[] timePoints = new TimePoint[n << 1];
        for (int i = 0; i < n; i++) {
            // 开始
            timePoints[i] = new TimePoint(jobs[i][0], jobs[i][1], true);
            // 结束
            timePoints[i + n] = new TimePoint(jobs[i][1], jobs[i][1], false);
        }
        // 根据开始时间排个序
        Arrays.sort(timePoints, (a, b) -> a.start - b.start);
        // 来一个小根堆 . 算结束时间
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // 遍历每一个任务对象组
        for (int i = 0, lastTime = timePoints[0].start; i < timePoints.length; i++) {
            if (timePoints[i].add) { // 是添加事件。直接添加
                heap.add(timePoints[i].end);
            }
            // 不是添加事件，那么就一定是该结算事件
            else {
                int curTime = timePoints[i].start;// 当前来到的时间
                // 从上一次的时间到本次时间 . 结算这期间的
                for (int j = lastTime; j < curTime; j++) {
                    if (heap.isEmpty()) { // 还没有需要结算的，那么本次任务就直接跳过，都没有任务需要结算的
                        break;
                    }
                    heap.poll();
                }
                // 当前栈顶是结束时间，如果都小于了当前任务的开始时间了，一定做不完
                // [1,3] [2,6],[3,6] ,[4,5]
                // 1 2 3 4 5 6
                // 有更小的开始，就结算不了了
                if (heap.peek() <= curTime) {
                    return false;
                }
                lastTime = curTime;
            }
        }
        return true;
    }

    public static class TimePoint {
        public int start;
        public int end;
        // add = ture 表示任务是添加的
        // add = false 表示任务是结束
        public boolean add;

        public TimePoint(int s, int e, boolean a) {
            start = s;
            end = e;
            add = a;
        }
    }

}
