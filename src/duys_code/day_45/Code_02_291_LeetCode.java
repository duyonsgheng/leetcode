package duys_code.day_45;

import java.util.HashSet;

/**
 * @ClassName Code_02_291_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/word-pattern-ii/
 * @Date 2021/10/18 14:09
 **/
public class Code_02_291_LeetCode {
    /**
     * 题意：
     * 给你一种规律 pattern 和一个字符串 str，请你判断 str 是否遵循其相同的规律。
     * 这里我们指的是 完全遵循，例如 pattern 里的每个字母和字符串 str 中每个 非空 单词之间，存在着双向连接的对应规律。
     * 示例1:
     * 输入: pattern = "abab", str = "redblueredblue"
     * 输出: true
     * red -> a
     * blue -> b
     * <p>
     * 示例2:
     * 输入: pattern = "aaaa", str = "asdasdasdasd"
     * 输出: true
     * asd -> a
     */

    // 根据题意 都是小写字母
    // 最长的代指就只有26长度 String[] map -> new String[26]
    // s[si....] 是不是符合 p[pi....]符合就是true
    public static boolean process(String s, String p, int si, int pi, String[] map, HashSet<String> set) {
        // 都来到了最后了，搞定了
        if (pi == p.length() && si == s.length()) {
            return true;
        }
        // 有一个用完了，另外一个没用哇，都算失败
        if (pi == p.length() || si == s.length()) {
            return false;
        }
        // 来到了pi的位置
        char ch = p.charAt(pi);
        // 看看我们的字典里有没有已经存在了代指了
        String cur = map[ch - 'a'];
        if (cur != null) { // 之前已经存在了
            return si + cur.length() <= s.length() // 不能越界，就是我当前来到的位置，+ 已经存在了代指
                    && cur.equals(s.substring(si, si + cur.length())) // 这个代指是有效的
                    && process(s, p, si + cur.length(), pi + 1, map, set); // si和pi对应的代指已经搞定了，去下一个代指位置看看
        }
        // 如果还没有被代指
        // 那么从si出发所有的前缀都尝试
        int end = s.length();
        // 剪枝：比如p -> acbhk  来到了位置是b那么 h k只要得在s字符串中给剩下得每一个字符留一个字符对应，否则，流程就串不起来，
        // 如果剩下得p中得字符有代指了，那么还得在s中减去代指得那一部分长度
        for (int i = p.length() - 1; i > pi; i--) {
            end -= map[p.charAt(i) - 'a'] == null ? 1 : map[p.charAt(i) - 'a'].length();
        }

        for (int i = si; i < end; i++) {
            cur = s.substring(si, si + i);
            // 万一这个前缀串之前已经被其他的指定了，就不能尝试了
            if (set.contains(cur)) {
                continue;
            }
            set.add(cur);
            map[ch - 'a'] = cur;
            if (process(s, p, i + 1, pi + 1, map, set)) {
                return true;
            }
            map[ch - 'a'] = null;
            set.remove(cur);
        }
        return false;
    }
}
