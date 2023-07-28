package custom.code_2023_07;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2208
 * @date 2023年07月25日
 */
// 2208. 将数组和减半的最少操作次数
// https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/
public class LeetCode_2208 {
    // 优先级队列
    // 照大的来砍半
    public int halveArray(int[] nums) {
        PriorityQueue<Double> queue = new PriorityQueue<>((a, b) -> b.compareTo(a));
        double sum = 0;
        int ans = 0;
        for (int num : nums) {
            queue.offer((double) num);
            sum += num;
        }
        double cur = 0d;
        while (cur < sum / 2) {
            double x = queue.poll();
            cur += x / 2;
            queue.offer(x / 2);
            ans++;
        }
        return ans;
    }
}
