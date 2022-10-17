package custom.code_2022_10;

import java.util.Stack;

/**
 * @ClassName LeetCode_907
 * @Author Duys
 * @Description
 * @Date 2022/10/17 9:16
 **/
// 907. 子数组的最小值之和
public class LeetCode_907 {
    // 例如
    // 3,1,2,4,1
    // 下标0 3为最小时，辐射范围是[3]
    // 下标1 1为最小时，辐射范围是[3,1,2,4,1]
    // 下标2 2为最小时，辐射范围是[2,4]
    // 下标3 4为最小时，辐射范围是[4]
    // 下标4 1为最小时，辐射范围是[2,4,1]
    // 那么我们来到一个位置就看左边到不了的和右边到不了的，然后l到r区间内所有的子数组，乘以这个最小值就可以了、。l-r区间内左右的子数组，等差数列求和公式
    int mod = 1000000007;

    public int sumSubarrayMins(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 把大于当前位置的数弹掉，找到最左的小于自己的
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                left[i] = -1;
            } else {
                left[i] = stack.peek();
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                right[i] = n;
            } else
                right[i] = stack.peek();
            stack.push(i);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long) (i - left[i]) * (right[i] - i) * arr[i]) % mod;
        }
        return (int)ans;
    }
}
