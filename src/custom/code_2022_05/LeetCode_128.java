package custom.code_2022_05;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName LeetCode_128
 * @Author Duys
 * @Description
 * @Date 2022/5/30 17:08
 **/
// 128. 最长连续序列
// 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
//请你设计并实现时间复杂度为O(n) 的算法解决此问题。
//链接：https://leetcode.cn/problems/longest-consecutive-sequence
public class LeetCode_128 {

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length < 2) {
            return 1;
        }
        Queue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            if (!heap.isEmpty() && heap.contains(num)) {
                continue;
            }
            heap.add(num);
        }
        int ans = 1;
        int res = 1;
        int pre = heap.poll();
        while (!heap.isEmpty()) {
            int cur = heap.poll();
            if (cur == pre + 1) {
                ans++;
                res = Math.max(res, ans);
            } else {
                ans = 1;
            }
            pre = cur;
        }
        return res;
    }

    public static int longestConsecutive1(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 0, 1};
        System.out.println(longestConsecutive1(arr));
    }
}
