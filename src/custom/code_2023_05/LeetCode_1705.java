package custom.code_2023_05;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1705
 * @Author Duys
 * @Description
 * @Date 2023/5/6 14:50
 **/
// 1705. 吃苹果的最大数目
public class LeetCode_1705 {
    public int eatenApples(int[] apples, int[] days) {
        int ans = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = apples.length;
        int i = 0;
        while (i < n) {
            // 把腐烂的苹果弹出
            while (!queue.isEmpty() && queue.peek()[0] <= i) {
                queue.poll();
            }
            // 当前苹果将会在第几天腐烂
            int rottenDay = i + days[i];
            int count = apples[i];
            if (count > 0) {
                queue.offer(new int[]{rottenDay, count});
            }
            if (!queue.isEmpty()) {
                int[] cur = queue.poll();
                // 当前消耗一个苹果
                cur[1]--;
                if (cur[1] > 0) {
                    queue.offer(cur);
                }
                ans++;
            }
            i++;
        }
        // 看看剩下的苹果能吃几天
        while (!queue.isEmpty()) {
            // 过期，腐烂的全部弹出
            while (!queue.isEmpty() && queue.peek()[0] <= i) {
                queue.poll();
            }
            // 没了，吃个卵
            if (queue.isEmpty()) {
                break;
            }
            int[] cur = queue.poll();
            // 有几个，或者能吃几天
            int curDay = Math.min(cur[0] - i, cur[1]);
            ans += curDay;
            // 天数往后延迟，方便后面滤掉哪些过期的苹果
            i += curDay;
        }
        return ans;
    }
}
