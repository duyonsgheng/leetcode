package custom.code_2022_07;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_205
 * @Author Duys
 * @Description
 * @Date 2022/7/6 14:42
 **/
// 205. 同构字符串
// 给定两个字符串s和t，判断它们是否是同构的。
//如果s中的字符可以按某种映射关系替换得到t，那么这两个字符串是同构的。
//每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
//链接：https://leetcode.cn/problems/isomorphic-strings
public class LeetCode_205 {
    public static boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        Map<Character, String> countS = new HashMap<>();
        Map<Character, String> countT = new HashMap<>();
        char[] str = s.toCharArray();
        char[] ttr = t.toCharArray();
        StringBuffer sbuffer = new StringBuffer();
        StringBuffer tbuffer = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            String j = i < 10 ? "0" + i : "" + i;
            char sc = str[i];
            char tc = ttr[i];
            if (countS.containsKey(sc)) {
                j = countS.get(sc);
            }
            sbuffer.append(j);
            countS.put(sc, i < 10 ? "0" + i : "" + i);
            j = i < 10 ? "0" + i : "" + i;
            if (countT.containsKey(tc)) {
                j = countT.get(tc);
            }
            tbuffer.append(j);
            countT.put(tc, i < 10 ? "0" + i : "" + i);
        }
        return sbuffer.toString().equals(tbuffer.toString());
    }

    public static void main(String[] args) {
        // "abcdefghijklmnopqrstuvwxyzva"
        //"abcdefghijklmnopqrstuvwxyzck"
        String t1 = "foo";
        String t2 = "bar";
        System.out.println(isIsomorphic(t1, t2));
    }
}
