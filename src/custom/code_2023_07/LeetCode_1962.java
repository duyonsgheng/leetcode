package custom.code_2023_07;

import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1962
 * @date 2023年07月17日
 */
// 1962. 移除石子使总数最小
// https://leetcode.cn/problems/remove-stones-to-minimize-the-total/
public class LeetCode_1962 {
    //  6 7 8 9 32 12 23
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i : piles) {
            queue.add(i);
        }
        while (k != 0) {
            int cur = queue.poll();
            queue.add(cur - (cur / 2));
            k--;
        }
        int sum = 0;
        while (!queue.isEmpty())
            sum += queue.poll();
        return sum;
    }
}
