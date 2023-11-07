package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2116
 * @date 2023年08月18日
 */
// 2116. 判断一个括号字符串是否有效
// https://leetcode.cn/problems/check-if-a-parentheses-string-can-be-valid/
public class LeetCode_2116 {
    // "))()))", locked = "010100"
    public boolean canBeValid(String s, String locked) {
        int l = 0, n = s.length(), change = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            char b = locked.charAt(i);
            if (c == '(') {
                // 可以边为右括号
                if (b == '0' && l > 0) {
                    l--;
                    change++; // 可以变化的位置+1，左括号-1
                } else {
                    l++;
                }
            } else {
                if (b == '0') {
                    if (l == 0) {
                        l++;
                    } else {
                        l--;
                        change++;
                    }
                } else {
                    // 前面没有可以用的了，
                    if (l == 0 && change == 0) {
                        return false;
                    }
                    if (l > 0) {
                        l--;
                    } else {
                        change--; // 使用一个，变为左括号
                        l++;
                    }
                }
            }
        }
        return l == 0;
    }
}
