package hope.class92;

import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Code06_MinimumNumberRefuelingStops
 * @date 2024年09月10日 下午 04:28
 */

// 最低加油次数
// 汽车从起点出发驶向目的地，该目的地位于出发位置东面target英里处
// 沿途有加油站，用数组stations表示，其中 stations[i] = [positioni, fueli]
// 表示第i个加油站位于出发位置东面positioni英里处，并且有fueli升汽油
// 假设汽车油箱的容量是无限的，其中最初有startFuel升燃料
// 它每行驶1英里就会用掉1升汽油
// 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中
// 为了到达目的地，汽车所必要的最低加油次数是多少？
// 如果无法到达目的地，则返回-1
// 注意：如果汽车到达加油站时剩余燃料为0，它仍然可以在那里加油
// 如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地
// 测试链接 : https://leetcode.cn/problems/minimum-number-of-refueling-stops/
public class Code06_MinimumNumberRefuelingStops {
    // 沿途收集加油站，当当前油量不够到下一个加油站或者目的地的时候，就从收集到的加油站中，加最大油量
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (startFuel >= target) {
            return 0;
        }
        // 大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        int to = startFuel;
        int cnt = 0;
        for (int[] sa : stations) {
            int position = sa[0];
            int fuel = sa[1];
            if (to < position) { // 到不了下一个目的地，就要去加油了
                while (!heap.isEmpty() && to < position) {
                    to += heap.poll();
                    cnt++; // 算加了一次油了
                    if (to >= target) {
                        return cnt;
                    }
                }
                // 把之前的油都加完了，都到不了
                if (to < position) {
                    return -1;
                }
            }
            heap.add(fuel);
        }
        // 如果还没有到目的地，并且还有油，就一直加
        while (!heap.isEmpty()) {
            to += heap.poll();
            cnt++;
            if (to >= target) {
                return cnt;
            }
        }
        return -1;
    }
}
