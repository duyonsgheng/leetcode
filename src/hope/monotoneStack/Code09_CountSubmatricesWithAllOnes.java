package hope.monotoneStack;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code09_CountSubmatricesWithAllOnes
 * @date 2023年11月08日 9:35
 */
// 统计全1子矩形的数量
// 给你一个 m * n 的矩阵 mat，其中只有0和1两种值
// 请你返回有多少个 子矩形 的元素全部都是1
// 测试链接 : https://leetcode.cn/problems/count-submatrices-with-all-ones/
public class Code09_CountSubmatricesWithAllOnes {

    public static int MAXM = 151;

    public static int[] height = new int[MAXM];

    public static int[] stack = new int[MAXM];

    public static int r;

    // 直方图问题的扩展
    // 栈里元素是大压小
    // 来到当前位置的时候
    public static int numSubmat(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        Arrays.fill(height, 0, m, 0);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            ans += count(m);
        }
        return ans;
    }

    private static int count(int m) {
        r = 0;
        int ans = 0;
        // 最左边的边界  left
        // 当前矩形的长度 len
        // 当前矩形高度在 bottom 和 height[i]之间 求一个最大值，因为更小的是由其他的位置来sauna的
        for (int i = 0, left, len, bottom; i < m; i++) {
            while (r > 0 && height[stack[r - 1]] >= height[i]) {
                int cur = stack[--r];
                if (height[cur] > height[i]) {
                    left = r == 0 ? -1 : stack[r - 1];
                    len = i - left - 1;
                    bottom = Math.max(left == -1 ? 0 : height[left], height[i]);
                    ans += (height[cur] - bottom) * len * (len + 1) / 2;
                }
            }
            stack[r++] = i;
        }
        // 清算
        while (r > 0) {
            int cur = stack[--r];
            int left = r == 0 ? -1 : stack[r - 1];
            int len = m - left - 1;
            int dowm = left == -1 ? 0 : height[left];
            ans += (height[cur] - dowm) * len * (len + 1) / 2;
        }
        return ans;
    }

}
