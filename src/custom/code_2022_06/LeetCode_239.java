package custom.code_2022_06;


import java.util.LinkedList;

/**
 * @ClassName LeetCode_239
 * @Author Duys
 * @Description
 * @Date 2022/6/10 16:16
 **/
// 239. 滑动窗口最大值
// https://leetcode.cn/problems/sliding-window-maximum/
public class LeetCode_239 {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k < 1 || nums.length < k) {
            return null;
        }
        // 窗口内最大值更新结构
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] ans = new int[nums.length - k + 1];
        int index = 0;
        for (int R = 0; R < nums.length; R++) {
            // 当前元素进入窗口内，弹出哪些小于当前元素的值
            while (!qmax.isEmpty() && (nums[qmax.peekLast()] <= nums[R])) {
                qmax.pollLast(); // 一直剔除元素
            }
            // 加入当前位置
            qmax.addLast(R);
            // 如果起始位置都是大于窗口了，窗口大小是超过了，窗口收缩
            if (qmax.peekFirst() == (R - k)) {
                qmax.pollFirst();
            }
            // 如果窗口内的元素够了可以收集了
            if (R >= k - 1) {
                ans[index++] = nums[qmax.peekFirst()];
            }
        }
        return ans;
    }

}
