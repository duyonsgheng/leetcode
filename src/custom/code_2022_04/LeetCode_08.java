package custom.code_2022_04;

/**
 * @ClassName LeetCode_08
 * @Author Duys
 * @Description
 * @Date 2022/4/26 13:15
 **/
// https://leetcode-cn.com/problems/palindrome-number/
public class LeetCode_08 {

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x == Integer.MIN_VALUE || Integer.MAX_VALUE == x) {
            return false;
        }
        if (x <= 9) {
            return true;
        }
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        int l = 0;
        int r = chars.length - 1;
        while (l <= r) {
            if (chars[l++] != chars[r--]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(1000021));
    }
}
