package custom.code_2022_05;

/**
 * @ClassName LeetCode_58
 * @Author Duys
 * @Description
 * @Date 2022/5/6 13:12
 **/
// 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
//单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/length-of-last-word
public class LeetCode_58 {
    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        if (s.length() == 1) {
            return s.equals(" ") ? 0 : 1;
        }
        char[] str = s.toCharArray();
        int fast = str.length - 1;
        int last = str.length - 1;
        while (fast >= 0) {
            if (str[fast] != ' ') {
                break;
            } else {
                fast--;
            }
        }
        last = fast;
        while (last >= 0) {
            if (str[last] == ' ') {
                break;
            } else last--;
        }
        return fast - last;
    }

    public static void main(String[] args) {
        String aa = " a";
        System.out.println(lengthOfLastWord(aa));
    }
}
