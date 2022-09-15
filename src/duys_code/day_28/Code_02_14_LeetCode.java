package duys_code.day_28;

/**
 * @ClassName Code_02_14_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/longest-common-prefix/
 * @Date 2021/11/23 11:04
 **/
public class Code_02_14_LeetCode {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length <= 0) {
            return "";
        }
        String cur = strs[0];
        int comm = cur.length();
        for (int i = 1; i < strs.length; i++) {
            comm = Math.min(comm(cur, strs[i]), comm);
        }
        if (comm == 0) {
            return "";
        }
        return cur.substring(0, comm);
    }

    public static int comm(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int index = 0;
        for (; index < str1.length && index < str2.length; index++) {
            if (str1[index] != str2[index]) {
                return index;
            }
        }
        return index;
    }
}
