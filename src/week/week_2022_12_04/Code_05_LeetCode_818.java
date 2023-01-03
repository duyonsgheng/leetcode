package week.week_2022_12_04;

import java.util.PriorityQueue;

/**
 * @ClassName Code_05_LeetCode_818
 * @Author Duys
 * @Description
 * @Date 2022/12/29 11:32
 **/
// 818. 赛车
public class Code_05_LeetCode_818 {
    // 1.分析到到达target有几种状态，速度为正达到，速度为负达到
    // 2.达到target最大的速度不可能会超过 <= target的某个数的2次方
    // 使用dj算法
    public int racecar(int target) {
        int maxp = 0;
        int maxs = 1;
        while (maxp <= target) {
            maxp += (1 << maxs - 1);
            maxs++;
        }
        // 标准的dijk算法流程
        // int[]  [0] - 整数和负数 表示速度 [1]-表示花费了多少步 [2] 当前的位置
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[][] positive = new boolean[maxs + 1][maxp + 1]; // 以正速度达到
        boolean[][] negative = new boolean[maxs + 1][maxp + 1];// 以负的速度达到
        queue.add(new int[]{1, 0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int speed = cur[0];
            int cost = cur[1];
            int position = cur[2];
            if (position == target) {
                return cost;
            }
            // 速度为正
            if (speed > 0) {
                if (positive[speed][position]) {
                    continue;
                }
                positive[speed][position] = true;
                // 可以继续以当前的速度往前走
                add(speed + 1, cost + 1, position + (1 << (speed - 1)), maxp, queue, positive);
                // 也可以掉头
                add(-1, cost + 1, position, maxp, queue, negative);
            } else {
                speed = -speed;
                if (negative[speed][position]) {
                    continue;
                }
                negative[speed][position] = true;
                add(-speed - 1, cost + 1, position - (1 << (speed - 1)), maxp, queue, negative);
                add(1, cost + 1, position, maxp, queue, positive);
            }
        }
        return -1;
    }

    public void add(int speed, int cost, int position, int limit, PriorityQueue<int[]> queue, boolean[][] visited) {
        if (position >= 0 && position <= limit && !visited[Math.abs(speed)][position]) {
            queue.add(new int[]{speed, cost, position});
        }
    }
}
