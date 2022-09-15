package custom.code_2022_08;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName LeetCode_524
 * @Author Duys
 * @Description
 * @Date 2022/8/22 13:10
 **/
// 524. 通过删除字母匹配到字典里最长单词
// 给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
//如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
//链接：https://leetcode.cn/problems/longest-word-in-dictionary-through-deleting
public class LeetCode_524 {

    // 1.先排序
    public String findLongestWord(String s, List<String> dictionary) {
        Collections.sort(dictionary, (s1, s2) -> {
            // 1.长的排前面
            // 2.字典序小的排前面
            return s1.length() != s2.length() ? s2.length() - s1.length() : s1.compareTo(s2);
        });
        for (String str : dictionary) {
            int i = 0;
            int j = 0;
            // 如果str中所有的字母都在s中，并且顺序也是一致的，就满足
            while (i < str.length() && j < s.length()) {
                if (str.charAt(i) == s.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == str.length()) {
                return str;
            }
        }
        return null;
    }
}
