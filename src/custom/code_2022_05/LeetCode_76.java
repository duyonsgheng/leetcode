package custom.code_2022_05;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName LeetCode_76
 * @Author Duys
 * @Description
 * @Date 2022/5/11 13:11
 **/
// 76. 最小覆盖子串
// 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//注意：
//对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
//如果 s 中存在这样的子串，我们保证它是唯一的答案。
//链接：https://leetcode.cn/problems/minimum-window-substring
public class LeetCode_76 {
    // 窗口解决
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < 1 || t.length() < 1 || s.length() < t.length()) {
            return "";
        }
        int tLen = t.length();
        Map<Character, Integer> ori = new HashMap<>();
        Map<Character, Integer> cnt = new HashMap<>();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            // 当前遍历中增加某些字符的个数
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            // 看看当前是否满足，如果满足，就去更新位置
            while (check(cnt, ori) && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                // 窗口缩进的时候，需要减去词频
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public static boolean check(Map<Character, Integer> cnt, Map<Character, Integer> ori) {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
}
