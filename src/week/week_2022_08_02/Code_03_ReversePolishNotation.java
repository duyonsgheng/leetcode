package week.week_2022_08_02;

import java.util.Stack;

/**
 * @ClassName Code_03_ReversePolishNotation
 * @Author Duys
 * @Description
 * @Date 2022/8/11 9:51
 **/
// 给定一个逆波兰式
// 转化成正确的中序表达式
// 要求只有必要加括号的地方才加括号
public class Code_03_ReversePolishNotation {

    // 1.给定一个正确的逆波兰式，请求出答案
    public static int getAns(String rpn) {
        if (rpn == null || rpn.equals("")) {
            return 0;
        }
        String[] str = rpn.split(" ");
        Stack<Integer> stack = new Stack<>();
        for (String s : str) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                int right = stack.pop();
                int left = stack.pop();
                int ans = 0;
                if (s.equals("+")) {
                    ans = left + right;
                } else if (s.equals("-")) {
                    ans = left - right;
                } else if (s.equals("*")) {
                    ans = left * right;
                } else {
                    ans = left / right;
                }
                stack.push(ans);
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        // 算到最后只剩下一个，就是返回的答案
        return stack.pop();
    }

    // 2.给定一个逆波兰式，返回计算式
    enum Operation {
        NUMBER, // 仅仅是数字
        ADD_OR_MINUS, // 是一个 + -计算式整体
        MULTIPLY_OR_DIVIDE// 是一个 * / 计算式整体
    }

    public static String convert(String rpn) {
        if (rpn == null || rpn.equals("")) {
            return "";
        }
        // 准备两个栈
        Stack<String> str = new Stack<>();
        Stack<Operation> type = new Stack<>();
        String[] s = rpn.split(" ");
        for (String cur : s) {
            if (cur.equals("+") || cur.equals("-")) {
                String right = str.pop();
                String left = str.pop();
                // 弹出类型
                type.pop();
                type.pop();
                str.push(left + cur + right);
                type.push(Operation.ADD_OR_MINUS);
            } else if (cur.equals("*") || cur.equals("/")) {
                String right = str.pop();
                String left = str.pop();
                Operation rightOp = type.pop();
                Operation leftOp = type.pop();
                String l = leftOp == Operation.ADD_OR_MINUS ? "(" + left + ")" : left;
                String r = rightOp == Operation.ADD_OR_MINUS ? "(" + right + ")" : right;
                str.push(l + cur + r);
                type.push(Operation.MULTIPLY_OR_DIVIDE);
            } else {
                str.push(cur);
                type.push(Operation.NUMBER);
            }
        }
        return str.pop();
    }
}
