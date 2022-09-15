package duys_code.day_28;

/**
 * @ClassName Code_05_20_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/valid-parentheses/
 * @Date 2021/11/23 14:59
 **/
public class Code_05_20_LeetCode {

    public boolean isValid(String s) {
        if (s == null || s.length() <= 0) {
            return false;
        }
        char[] str = s.toCharArray();
        // 数组替换栈
        char[] map = new char[str.length];
        int size = 0; // 栈中的元素
        for (int i = 0; i < str.length; i++) {
            char cha = str[i];
            // 压栈
            if (cha == '(' || cha == '[' || cha == '{') {
                map[size++] = cha == '(' ? ')' : (cha == '[' ? ']' : '}');
            }
            // 右括号就弹栈
            else {
                if (size == 0) {
                    return false;
                }
                char last = map[--size];
                if (last != cha) {
                    return false;
                }
            }
        }
        return size == 0;
    }
}
