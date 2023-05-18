package custom.code_2023_04;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1642
 * @Author Duys
 * @Description
 * @Date 2023/4/23 11:09
 **/
//1642.可以到达的最远建筑
public class LeetCode_1642 {
    // 堆，优先使用砖头或者优先使用梯子都行
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        // 小根堆，差距量最大得放地下用梯子来解决
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int sum = 0;
        for (int i = 1; i < n; i++) {
            int diff = heights[i] - heights[i - 1];
            if (diff <= 0) {
                continue;
            }
            queue.add(diff);
            // 大于梯子数量
            if (queue.size() > ladders) {
                sum += queue.poll();
            }
            // 多余得位置
            if (sum > bricks) {
                return i - 1;
            }
        }
        return n - 1;
    }
}
