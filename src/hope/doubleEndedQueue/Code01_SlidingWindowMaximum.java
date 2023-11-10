package hope.doubleEndedQueue;

/**
 * @author Mr.Du
 * @ClassName Code01_SlidingWindowMaximum
 * @date 2023年11月08日 10:00
 */
// 滑动窗口最大值（单调队列经典用法模版）
// 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧
// 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
// 返回 滑动窗口中的最大值 。
// 测试链接 : https://leetcode.cn/problems/sliding-window-maximum/
public class Code01_SlidingWindowMaximum {
    public static int MAXN = 100001;

    public static int[] deque = new int[MAXN];

    // 队列内的头和尾
    public static int h, t;

    // 单调队列的经典用法
    // 窗口内的最大值最小值更新结构
    public static int[] maxSlidingWindow(int[] arr, int k) {
        h = t = 0;
        int n = arr.length;
        // 先来一个基础窗口
        for (int i = 0; i < k - 1; i++) {
            // 如果当前队列里面尾巴的值小于当前值，那么当前值就需要往前浮动
            while (h < t && arr[deque[t - 1]] <= arr[i]) {
                t--;
            }
            deque[t++] = i;
        }
        // 剩下的多少
        int m = n - k + 1;
        int[] ans = new int[m];
        for (int l = 0, r = k - 1; l < m; l++, r++) {
            // 1.先把r位置的数拉进来，
            // 2.看看是否能更新最大值
            while (h < t && arr[deque[t - 1]] <= arr[r]) {
                t--;
            }
            deque[t++] = r;
            // 3.收集答案
            ans[l] = arr[deque[h]];
            // 4.l位置的数马上出窗口了，看看是否更新了
            if (deque[h] == l) {
                h++;
            }
        }
        return ans;
    }
}
