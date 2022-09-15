package custom.code_2022_08;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @ClassName LeetCode_456
 * @Author Duys
 * @Description
 * @Date 2022/8/12 14:08
 **/
// 132 模式
// 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
//链接：https://leetcode.cn/problems/132-pattern
public class LeetCode_456 {

    public static boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        // [3,1,4,2]
        // 记录左边最小和右边最小分别在哪些位置
        int n = nums.length;
        // 大压小
        Deque<Integer> stack = new LinkedList<>();
        int k = Integer.MIN_VALUE;
        // 从右往左记录最大的
        // 如果从右往左遇到了递减，就满足
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < k) {
                return true;
            }
            while (!stack.isEmpty() && stack.peekLast() < nums[i]) {
                k = Math.max(k, stack.pollLast());
            }
            stack.offerLast(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 0, 1, -4, -3};
        System.out.println(find132pattern(arr1));
        int[] arr2 = {3, 1, 4, 2};
        System.out.println(find132pattern(arr2));
        int[] arr3 = {-1, 3, 2, 0};
        System.out.println(find132pattern(arr3));
    }
}
