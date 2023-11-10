package hope.monotoneStack;

/**
 * @author Mr.Du
 * @ClassName Code06_MaximumWidthRamp
 * @date 2023年11月07日 11:42
 */
// 最大宽度坡
// 给定一个整数数组A，坡是元组(i, j)，其中i < j且A[i] <= A[j]
// 这样的坡的宽度为j - i，找出A中的坡的最大宽度，如果不存在，返回 0
// 测试链接 : https://leetcode.cn/problems/maximum-width-ramp/
public class Code06_MaximumWidthRamp {
    public static int MAXN = 50001;

    public static int[] stack = new int[MAXN];

    public static int r;

    // 栈例保持小压大，遇到大的就开始结算
    public static int maxWidthRamp(int[] arr) {
        r = 1;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            // 栈顶元素是大于当前位置的，入栈
            if (arr[stack[r - 1]] > arr[i]) {
                stack[r++] = i;
            }
        }
        int ans = 0;
        // 从后往前结算
        for (int i = n - 1; i >= 0; i--) {
            // 之前的数小于当前的，开始结算
            while (r > 0 && arr[stack[r - 1]] <= arr[i]) {
                ans = Math.max(ans, i - stack[--r]);
            }
        }
        return ans;
    }
}
