package hope.class90;

import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Code05_IPO
 * @date 2024年08月26日 下午 05:28
 */
// IPO
// 给你n个项目，对于每个项目i
// 它都有一个纯利润profits[i]
// 和启动该项目需要的最小资本capital[i]
// 最初你的资本为w，当你完成一个项目时，你将获得纯利润，添加到你的总资本中
// 总而言之，从给定项目中选择最多k个不同项目的列表
// 以最大化最终资本，并输出最终可获得的最多资本
// 测试链接 : https://leetcode.cn/problems/ipo/
public class Code05_IPO {
    // 两个堆，一个小根堆，根据项目的启动资金的小根堆，记录被锁定的任务
    // 一个大根堆，根据纯利润的大根堆，记录当前那些任务可以做，已解锁的任务
    // 然后选择利润较大的执行
    public static int findMaximizedCapital(int k, int w, int[] profit, int[] cost) {
        PriorityQueue<int[]> heap1 = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> heap2 = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int n = profit.length;
        for (int i = 0; i < n; i++) {
            heap1.add(new int[]{profit[i], cost[i]});
        }
        while (k > 0) {
            // 1.解锁任务
            while (!heap1.isEmpty() && heap1.peek()[1] <= w) {
                heap2.add(heap1.poll());
            }
            // 2.验证是不是剩下的任务都不能做了
            if (heap2.isEmpty()) {
                break;
            }
            // 3.选择利润最大的任务做
            w += heap2.poll()[1];
            k--;
        }
        return w;
    }
}
