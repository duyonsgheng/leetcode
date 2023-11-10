package hope.monotoneStack;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code05_MaximalRectangle
 * @date 2023年11月07日 11:18
 */
// 最大矩形
// 给定一个仅包含 0 和 1 、大小为 rows * cols 的二维二进制矩阵
// 找出只包含 1 的最大矩形，并返回其面积
// 测试链接：https://leetcode.cn/problems/maximal-rectangle/
public class Code05_MaximalRectangle {
    public static int MAXN = 201;

    public static int[] height = new int[MAXN];

    public static int[] stack = new int[MAXN];

    public static int r;

    // 数组压缩技巧，并且使用直方图问题求解
    public static int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        // 把之前行的数组压缩到一个数组来，然后每一个数组跑一次直方图问题
        Arrays.fill(height, 0, m, 0);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 如果当前位置是0了，那么就断联了
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            ans = Math.max(ans, findMax(m));
        }
        return ans;
    }

    public static int findMax(int m) {
        r = 0;
        int ans = 0, cur, left;
        for (int i = 0; i < m; i++) {
            // 满足大压小，如果违反了则需要结算之前的答案，看看之前入栈的位置能扩多远
            while (r > 0 && height[stack[r - 1]] >= height[i]) {
                cur = stack[--r];
                left = r == 0 ? -1 : stack[r - 1];
                // 以当前位置为高，然后能扩多远，然后计算答案
                ans = Math.max(ans, height[cur] * (i - left - 1));
            }
            stack[r++] = i;
        }
        // 清算剩余
        while (r > 0) {
            cur = stack[--r];
            left = r == 0 ? -1 : stack[r - 1];
            // 以当前位置为高，然后能扩多远，然后计算答案
            ans = Math.max(ans, height[cur] * (m - left - 1));
        }
        return ans;
    }
}
