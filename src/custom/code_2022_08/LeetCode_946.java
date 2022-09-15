package custom.code_2022_08;

import java.util.Stack;

/**
 * @ClassName LeetCode_946
 * @Author Duys
 * @Description
 * @Date 2022/8/31 8:34
 **/
public class LeetCode_946 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (popped == null || popped == null || popped.length != pushed.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int n = pushed.length;
        for (int i = 0, j = 0; i < n; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
