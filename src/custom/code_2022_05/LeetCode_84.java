package custom.code_2022_05;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @ClassName LeetCode_84
 * @Author Duys
 * @Description
 * @Date 2022/5/16 14:34
 **/
//84. 柱状图中最大的矩形
// 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//求在该柱状图中，能够勾勒出来的矩形的最大面积。
public class LeetCode_84 {


    /**
     * 思路：单调栈
     * 1.必须以每一个位置作为高度的长方形最多可以扩多远
     * 2.单调栈，大压小，违反了就计算面积
     */
    public static int largestRectangleArea(int[] heights) {
        //
        if (heights == null || heights.length <= 0) {
            return 0;
        }
        int len = heights.length;
        if (len == 1) {
            return heights[0];
        }
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < len; i++) {
            // 长方形以当前位置做高，可以扩多远
            // 如果当前的高是小于等于之前的，结算一下答案
            // 当前位置如果不能满足大压小，就结算前一个位置的
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int j = stack.pop();// 前一个位置到不了的最右地方
                int k = stack.isEmpty() ? -1 : stack.peek(); // 前一个位置到不了最左的位置
                int curWith = (i - k - 1) * heights[j];// 当前位置的
                ans = Math.max(ans, curWith);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();// 前一个位置到不了的最右地方
            int k = stack.isEmpty() ? -1 : stack.peek(); // 前一个位置到不了最左的位置
            int curWith = (len - k - 1) * heights[j];// 当前位置的
            ans = Math.max(ans, curWith);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(arr));
    }
}
