package custom.code_2022_11;

import java.util.Stack;

// 2375. 根据模式串构造最小数字
public class LeetCode_2375 {

    // 使用栈来做
    // 逆向思维
    // 遇到下降的时候 就直接压栈，如果遇到了上升，就直接添加结果，然后弹栈
    public String smallestNumber(String pattern) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int num = 1; // 从小到大的变化
        int n = pattern.length();
        for (int i = 0; i < n; i++) {
            char cur = pattern.charAt(i);
            if (cur == 'I') {
                sb.append(num);
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
            } else {
                stack.push(num);
            }
            num++;
        }
        stack.push(num);
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
