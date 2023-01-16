package custom.code_2023_01;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_1438
 * @Author Duys
 * @Description
 * @Date 2023/1/4 17:07
 **/
// 1438. 绝对差不超过限制的最长连续子数组
public class LeetCode_1438 {
    // 窗口，记录窗口内最大值和最小值
    // 然后窗口往右扩，如果破坏了就左边出，直到满足位置
    // nums = [8,2,4,7], limit = 4
    public int longestSubarray(int[] nums, int limit) {
        int ans = 0;
        int n = nums.length;
        Deque<Integer> maxQueue = new LinkedList<>();
        Deque<Integer> minQueue = new LinkedList<>();
        for (int r = 0, l = 0; r < n; r++) {
            // 弹出队列中不足自己的记录
            while (!maxQueue.isEmpty() && nums[r] >= nums[maxQueue.peekLast()]) {
                maxQueue.pollLast();
            }
            while (!minQueue.isEmpty() && nums[r] <= nums[minQueue.peekLast()]) {
                minQueue.pollLast();
            }
            maxQueue.addLast(r);
            minQueue.addLast(r);
            // 如果大于了limit，窗口缩进并且清理掉两个队列中哪些过期的位置
            while (Math.abs(nums[maxQueue.peekFirst()] - nums[minQueue.peekFirst()]) > limit) {
                l++;
                if (maxQueue.peekFirst() < l) {
                    maxQueue.pollFirst();
                }
                if (minQueue.peekFirst() < l) {
                    minQueue.pollFirst();
                }
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
