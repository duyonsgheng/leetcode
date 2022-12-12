package custom.code_2022_12;

/**
 * @ClassName LeetCode_1328
 * @Author Duys
 * @Description
 * @Date 2022/12/12 11:42
 **/
// 1328. 破坏回文串
public class LeetCode_1328 {
    public String breakPalindrome(String palindrome) {
        int len = palindrome.length();
        if (len == 1) {
            return "";
        }
        char[] chars = palindrome.toCharArray();
        boolean flag = false;
        for (int i = 0; i < len / 2; i++) {
            // 把字符串中第一个不是a的替换为a
            if (chars[i] > 'a') {
                chars[i] = 'a';
                flag = true;
                break;
            }
        }
        if (!flag) {
            chars[len - 1] = 'b';
        }
        // 如果没有，说明全部都是a，最后一个替换为b
        return new String(chars);
    }
}
