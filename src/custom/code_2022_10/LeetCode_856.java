package custom.code_2022_10;

import java.util.Stack;

/**
 * @ClassName LeetCode_856
 * @Author Duys
 * @Description
 * @Date 2022/10/9 9:19
 **/
// 856. 括号的分数
public class LeetCode_856 {

    // 栈模拟
    // 如果是空字符，为0
    // ()()(()())
    // 可以拆分为 A+B 和 A 这种形式的
    // 所以遇到左括号压0.遇到右括号的时候，弹出前一个答案，
    // 如果是0，说明是A 这种形式的
    // 如果不是0，说明是A+B这种形式的
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int cur = stack.pop();
                stack.push(stack.pop() + Math.max(cur * 2, 1));
            }
        }
        return stack.pop();
    }

}
