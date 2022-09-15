package duys_code.day_24;

/**
 * @ClassName Code_07_316_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/remove-duplicate-letters/
 * @Date 2021/11/15 15:19
 **/
public class Code_07_316_LeetCode {
    /**
     * 贪心：
     * 1.建立词频表，从左往右，第一次遇到我们的词频中某一个字符的词频=0的时候
     * 2.选择 0 到当前位置这个区间中字典序最小的字符作为第一个
     * 3.然后从当前字符的下一个往后继续重复
     */
    public static String removeDuplicateLetters(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        int minASC = 0;
        for (int i = 0; i < s.length(); i++) {
            minASC = s.charAt(minASC) > s.charAt(i) ? i : minASC;
            // 找到了，第一个字符已经词频是0了。
            if (--map[s.charAt(i)] == 0) {
                break;
            }
        }
        // 当前的字典序最小的字符
        String cur = String.valueOf(s.charAt(minASC));
        // 下一个字符从最小字段序的后续位置开始，并且把已经选出的字符给去掉
        String next = removeDuplicateLetters(s.substring(minASC + 1).replaceAll(String.valueOf(s.charAt(minASC)), ""));
        return cur + next;
    }
}
