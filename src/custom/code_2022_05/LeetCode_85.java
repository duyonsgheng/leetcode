package custom.code_2022_05;

import java.util.Stack;

/**
 * @ClassName LeetCode_85
 * @Author Duys
 * @Description
 * @Date 2022/5/16 17:15
 **/
// 85. 最大矩形
// 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
public class LeetCode_85 {
    // 单调栈

    /**
     * 整体思路：
     * 子矩阵必须以第i行作为地基，哪一个矩阵的1最多，
     * 1 1 1 1 1
     * 1 0 1 1 1
     * 1 0 0 1 0
     * 0 1 0 1 1
     * 以第0行做地基
     * 第二行就是
     * 2 0 2 2 2
     * 然后以第二行做地基往下继续，遇到0的时候，就从新算
     * 然后数组压缩到最后一行去。
     * 然后到最后一行上做单调栈
     */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        // 直方图
        int ans = 0;
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // 压缩数组
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            // 每压缩一下，就去看看能不能推高答案
            ans = Math.max(ans, max(height));
        }
        return ans;
    }

    public static int max(int[] height) {
        if (height == null || height.length <= 0) {
            return 0;
        }
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            // 我们看看以当前数未基数，能扩到哪里去
            // 扩不动了，结算压着的数
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int lastLeft = stack.pop(); // 前一个数的有边界就是当前i位置
                int lastRight = stack.isEmpty() ? -1 : stack.peek();// 左边界就是压着的这个数
                ans = Math.max((i - lastRight - 1) * height[lastLeft], ans);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int lastLeft = stack.pop();
            int lastRight = stack.isEmpty() ? -1 : stack.peek();
            ans = Math.max((height.length - lastRight - 1) * height[lastLeft], ans);
        }
        return ans;
    }
}
