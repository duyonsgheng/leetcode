package custom.code_2023_05;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_1696
 * @Author Duys
 * @Description
 * @Date 2023/5/5 14:54
 **/
// 1696. 跳跃游戏 VI
public class LeetCode_1696 {
    // dp+窗口
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 1; i < n; i++) {
            while (!queue.isEmpty() && dp[i - 1] >= dp[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offerLast(i - 1);
            if (queue.peekFirst() < i - k) {
                queue.pollFirst();
            }
            dp[i] = dp[queue.peekFirst()] + nums[i];
        }
        return dp[n - 1];
    }
}
