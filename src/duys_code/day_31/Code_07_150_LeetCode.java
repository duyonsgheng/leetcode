package duys_code.day_31;

import java.util.Stack;

/**
 * @ClassName Code_07_150_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 * @Date 2021/12/3 15:17
 **/
public class Code_07_150_LeetCode {
    // 逆波兰式，用栈来实现
    public static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length <= 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        for (String str : tokens) {
            // 遇到符号就计算
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                compute(stack, str);
            } else {
                stack.push(Integer.valueOf(str));
            }
        }
        return stack.peek();
    }

    public static void compute(Stack<Integer> stack, String op) {
        int num2 = stack.pop(); //
        int num1 = stack.pop();
        int ans = 0;
        switch (op) {
            case "+":
                ans = num1 + num2;
                break;
            case "-":
                ans = num1 - num2;
                break;
            case "*":
                ans = num1 * num2;
                break;
            case "/":
                ans = num1 / num2;
                break;
        }
        stack.push(ans);
    }
}
