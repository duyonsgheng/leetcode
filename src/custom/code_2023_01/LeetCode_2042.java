package custom.code_2023_01;

import java.util.Stack;

/**
 * @ClassName LeetCode_2042
 * @Author Duys
 * @Description
 * @Date 2023/1/3 15:39
 **/
// 2042. 检查句子中的数字是否递增
public class LeetCode_2042 {
    public boolean areNumbersAscending(String s) {
        String[] arr = s.split(" ");
        Stack<Integer> stack = new Stack<>();
        for (String str : arr) {
            if (str.charAt(0) >= '0' && str.charAt(0) <= '9') {
                int i = Integer.valueOf(str);
                if (stack.isEmpty()) {
                    stack.add(i);
                } else {
                    if (stack.peek() < i) {
                        stack.add(i);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
