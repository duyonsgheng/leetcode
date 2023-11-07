package hope.towPoint;

/**
 * @author Mr.Du
 * @ClassName Code03_TrappingRainWater
 * @date 2023年10月31日 9:46
 */
// 接雨水
// 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
// 测试链接 : https://leetcode.cn/problems/trapping-rain-water/
public class Code03_TrappingRainWater {
    // 1.动态规划
    // 生成左边最大值，右边最大值结构，然后每个位置遍历
    public int trap1(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            ans += Math.max(0, Math.min(left[i], right[i]) - height[i]);
        }
        return ans;
    }

    // 2.双指针
    public int trap(int[] height) {
        int l = 1, r = height.length - 1, left = height[0], right = height[height.length - 1];
        int ans = 0;
        while (l <= r) {
            // 谁小就结算谁，然后跳下一个位置
            if (left <= right) {
                ans += Math.max(0, left - height[l]);
                left = Math.max(left, height[l++]);
            } else {
                ans += Math.max(0, right - height[r]);
                right = Math.max(right, height[r--]);
            }
        }
        return ans;
    }
}
