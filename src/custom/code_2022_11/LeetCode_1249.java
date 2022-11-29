package custom.code_2022_11;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @ClassName LeetCode_1249
 * @Author Duys
 * @Description
 * @Date 2022/11/29 11:20
 **/
// 1249. 移除无效的括号
public class LeetCode_1249 {

    public String minRemoveToMakeValid(String s) {
        StringBuffer bu = new StringBuffer();
        int n = s.length();
        char[] arr = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        Set<Integer> remove = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] == '(') {
                stack.push(i);
            }
            if (arr[i] == ')') {
                if (stack.isEmpty()) {
                    remove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty())
            remove.add(stack.pop());
        for (int i = 0; i < n; i++) {
            if (remove.contains(i)) {
                continue;
            }
            bu.append(arr[i]);
        }
        return bu.toString();
    }

}
