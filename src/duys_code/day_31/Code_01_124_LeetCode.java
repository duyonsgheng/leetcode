package duys_code.day_31;

/**
 * @ClassName Code_01_124_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/valid-palindrome/
 * @Date 2021/11/29 16:05
 **/
public class Code_01_124_LeetCode {

    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 0) {
            return true;
        }
        char[] str = s.toCharArray();
        // 两个指针
        int p1 = 0;
        int p2 = str.length - 1;
        while (p1 <= p2) {
            // 两个相等才比较。
            if (!(isNum(str[p1]) || isNumber(str[p1]))) {
                p1++;
                continue;
            }
            if (!(isNum(str[p2]) || isNumber(str[p2]))) {
                p2--;
                continue;
            }
            if (!equal(str[p1++], str[p2--])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isNum(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static boolean equal(char c1, char c2) {
        if (isNumber(c1) || isNumber(c2)) {
            return c1 == c2;
        }
        // a  A   32
        // b  B   32
        // c  C   32
        return (c1 == c2) || (Math.max(c1, c2) - Math.min(c1, c2) == 32);
    }

    public static void main(String[] args) {
        System.out.println('A' - '0');
        System.out.println('b' - '0');
    }
}
