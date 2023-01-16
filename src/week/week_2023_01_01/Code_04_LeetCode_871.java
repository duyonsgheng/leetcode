package week.week_2023_01_01;

import java.util.PriorityQueue;

/**
 * @ClassName Code_04_LeetCode_871
 * @Author Duys
 * @Description
 * @Date 2023/1/5 11:28
 **/
// 871. 最低加油次数
// https://leetcode.cn/problems/minimum-number-of-refueling-stops/
public class Code_04_LeetCode_871 {

    // 如果能到下一个加油站，那么就不加油吗，并且收集沿途的加油站的油量，使用大根堆来收集，发现到不了下一个站的时候
    // 从沿途收集的油量中弹出，直到到达target或者没有油了为止
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (startFuel >= target) {
            return 0;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        int to = startFuel;
        int ans = 0;
        for (int[] station : stations) {
            int position = station[0];
            int fuel = station[1];
            // 如果到不了了，就看看之前收集的油能不能让我到当前位置
            if (to < position) {
                while (!heap.isEmpty() && to < position) {
                    to += heap.poll();
                    ans++;
                    if (to >= target) {
                        return ans;
                    }
                }
                // 都没油了还不能到
                if (to < position) {
                    return -1;
                }
            }
            // 收集油量
            heap.add(fuel);
        }
        // 加油站都没了，还没到最后的target
        while (!heap.isEmpty()) {
            to += heap.poll();
            ans++;
            if (to >= target) {
                return ans;
            }
        }
        return -1;
    }
}
