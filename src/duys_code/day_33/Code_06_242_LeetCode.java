package duys_code.day_33;

/**
 * @ClassName Code_06_242_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/valid-anagram/
 * @Date 2021/12/6 14:05
 **/
public class Code_06_242_LeetCode {

    // 判断连个字符是不是相似的
    // 词频
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        int[] count = new int[256];
        for (int i = 0; i < s1.length; i++) {
            count[s1[i]]++;
        }
        for (int i = 0; i < s2.length; i++) {
            if (--count[s2[i]] < 0) {
                return false;
            }
        }
        return true;
    }
}
