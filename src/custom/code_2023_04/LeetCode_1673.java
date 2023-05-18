package custom.code_2023_04;

import java.util.Stack;

/**
 * @ClassName LeetCode_1673
 * @Author Duys
 * @Description
 * @Date 2023/4/25 16:33
 **/
// 1673. 找出最具竞争力的子序列
public class LeetCode_1673 {
    public int[] mostCompetitive(int[] nums, int k) {
        int len = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i] && stack.size() + len - i > k) {
                stack.pop();
            }
            if (stack.size() < k) {
                stack.add(i);
            }
        }
        int[] ans = new int[k];
        int i = k--;
        while (!stack.isEmpty()) {
            ans[i--] = nums[stack.pop()];
        }
        return ans;
    }
}
