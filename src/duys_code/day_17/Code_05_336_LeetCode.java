package duys_code.day_17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_05_336_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/palindrome-pairs/
 * @Date 2021/10/29 10:29
 **/
public class Code_05_336_LeetCode {
    /**
     * 回文对：
     * 给定一组 互不相同 的单词， 找出所有 不同 的索引对 (i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
     */

    /**
     * 1.我们把每一个单词都记录到map中
     * 2.然后我们从每一个单词的前缀开始尝试
     * 比如 aabba aa是回文，看看有没有 字符串后缀bba的逆序串然后加在aa的前边 形成 abb aa bba
     * 比如 aabba 是回文 看看有没有字符串是a的。放在前面
     * 比如 aabba 看看有咩有aabb的逆序串存在，直接放在a的后面就可以
     */
    public List<List<Integer>> palindromePairs(String[] words) {

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            res.addAll(findAll(words[i], i, map));
        }
        return res;
    }

    public static List<List<Integer>> findAll(String word, int index, Map<String, Integer> map) {
        List<List<Integer>> res = new ArrayList<>();
        String reverses = reverse(word);
        // 获取前缀为”“的字符串
        Integer rest = map.get("");
        // 自己就是一个回文字符串
        if (rest != null && rest != index && word.equals(reverses)) {
            // 把空添加到前缀和后缀上
            addRecord(res, rest, index);
            addRecord(res, index, rest);
        }
        //
        int[] rs = manacherss(word);
        int mid = rs.length >> 1;
        // 从中点开始往后
        for (int i = 1; i < mid; i++) {
            if (i - rs[i] == -1) {
                rest = map.get(reverses.substring(0, mid - i));
                if (rest != null && rest != index) {
                    addRecord(res, rest, index);
                }
            }
        }
        for (int i = mid + 1; i < rs.length; i++) {
            if (i + rs[i] == rs.length) {
                rest = map.get(reverses.substring((mid << 1) - i));
                if (rest != null && rest != index) {
                    addRecord(res, index, rest);
                }
            }
        }
        return res;
    }

    public static int[] manacherss(String word) {
        char[] mchs = manachercs(word);
        int[] rs = new int[mchs.length];
        int center = -1;
        int pr = -1;
        for (int i = 0; i < mchs.length; i++) {
            rs[i] = pr > i ? Math.min(rs[(center << 1) - i], pr - i) : 1;
            while (i + rs[i] < mchs.length && i - rs[i] > -1) {
                if (mchs[i + rs[i]] != mchs[i - rs[i]]) {
                    break;
                }
                rs[i]++;
            }
            if (i + rs[i] > pr) {
                pr = i + rs[i];
                center = i;
            }
        }
        return rs;
    }

    public static char[] manachercs(String word) {
        char[] chs = word.toCharArray();
        char[] res = new char[chs.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chs[index++];
        }
        return res;
    }

    public static void addRecord(List<List<Integer>> res, int left, int right) {
        List<Integer> newr = new ArrayList<>();
        newr.add(left);
        newr.add(right);
        res.add(newr);
    }


    // 反转字符串
    public static String reverse(String str) {
        char[] chs = str.toCharArray();
        int l = 0;
        int r = chs.length - 1;
        while (l < r) {
            char tmp = chs[l];
            chs[l++] = chs[r];
            chs[r--] = tmp;
        }
        return String.valueOf(chs);
    }
}
