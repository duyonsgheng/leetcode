package custom.code_2022_11;

import java.util.Stack;

/**
 * @ClassName LeetCode_1130
 * @Author Duys
 * @Description
 * @Date 2022/11/15 15:52
 **/
// 1130. 叶值的最小代价生成树
public class LeetCode_1130 {
    // 6,2,4
    public int mctFromLeafValues(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] >= stack.peek())
                ans += stack.pop() * Math.min(stack.peek(), arr[i]);
            stack.push(arr[i]);
        }
        while (stack.size() > 2) {
            ans += stack.pop() * stack.peek();
        }
        return ans;
    }
}
