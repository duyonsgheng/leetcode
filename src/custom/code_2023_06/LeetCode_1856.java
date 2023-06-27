package custom.code_2023_06;

import java.util.Arrays;
import java.util.Stack;

/**
 * @ClassName LeetCode_1856
 * @Author Duys
 * @Description
 * @Date 2023/6/8 9:39
 **/
// 1856. 子数组最小乘积的最大值
public class LeetCode_1856 {
    // 3,1,5,6,4,2
    // 以每个数作为子数组的最小值
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        Stack<Integer> stack = new Stack<>();

        int[] right = new int[n];
        Arrays.fill(right, n);
        for (int i = 0; i < n; i++) {
            // 如果当前值比栈顶小，那说明栈顶的位置右边第一个小的就是当前i位置
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        stack.clear();
        int[] left = new int[n];
        Arrays.fill(left, -1);
        for (int i = n - 1; i >= 0; i--) {
            // 如果当前值比栈顶小，那说明栈顶的位置左边第一个小的就是当前i位置
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long c = sum[right[i]] - sum[left[i] + 1];
            ans = Math.max(ans, nums[i] * c);
        }
        int mod = 1_000_000_007;
        return (int) (ans % mod);
    }
}
