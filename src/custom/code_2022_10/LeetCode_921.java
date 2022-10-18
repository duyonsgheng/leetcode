package custom.code_2022_10;

import java.util.Stack;

/**
 * @ClassName LeetCode_921
 * @Author Duys
 * @Description
 * @Date 2022/10/18 11:42
 **/
// 921. 使括号有效的最少添加
public class LeetCode_921 {
    public static void main(String[] args) {
        String s = "())";
        System.out.println(minAddToMakeValid(s));
    }

    public static int minAddToMakeValid(String s) {
        if (s == null || s.length() <= 0) {
            return 2;
        }
        // "()))(("
        Stack<Character> stack = new Stack<>();
        int ans = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                stack.push(c);
            }
            if (c == ')') {
                if (stack.isEmpty()) {
                    ans++;
                } else {
                    stack.pop();
                }
            }
        }
        return ans + stack.size();
    }

    public static int minAddToMakeValid1(String s) {
        if (s == null || s.length() <= 0) {
            return 2;
        }
        int left = 0;
        int right = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            }
            if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        return left + right;
    }
}
