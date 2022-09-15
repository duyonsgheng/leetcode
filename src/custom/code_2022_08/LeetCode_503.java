package custom.code_2022_08;

import java.util.Arrays;
import java.util.Stack;

/**
 * @ClassName LeetCode_503
 * @Author Duys
 * @Description
 * @Date 2022/8/17 15:30
 **/
// 503. 下一个更大元素 II
public class LeetCode_503 {

    public int[] nextGreaterElements(int[] nums) {
        // 循环数组
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < 2 * n - 1; i++) {
            // 如果当前的数大于了栈顶元素，那么就依次弹出
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ans[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ans;
    }
}
