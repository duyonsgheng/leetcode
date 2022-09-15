package duys_code.day_31;

import java.util.List;

/**
 * @ClassName Code_04_139_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/word-break/
 * @Date 2021/11/29 17:34
 **/
public class Code_04_139_LeetCode {


    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() <= 0 || wordDict == null || wordDict.size() <= 0) {
            return true;
        }
        TreePri root = new TreePri();
        for (String str : wordDict) {
            fill(root, str);
        }
        // 看看在s中的字符开头的单词，在前缀树中有没有
        char[] chars = s.toCharArray();
        int N = chars.length;
        // 首先尝试前缀是0-0 ，0 -1，0-2 等等如果0-0范围上为前缀，那么依赖 1-N-1 上能不能搞定。。
        // 所以我们需要的是 0位置的值，那么我们需要从后往前推
        // dp[i] 表示 word[i...] 能不能拆分
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;// 空的默认可以被分解
        for (int i = N - 1; i >= 0; i--) {
            TreePri cur = root;
            for (int end = i; end < N; end++) {
                cur = cur.next[chars[end] - 'a'];
                // 根本就没有这个开头的单词，换一个开头吧
                if (cur == null) {
                    break;
                }
                if (cur.end) {
                    dp[i] |= dp[end + 1];
                }
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[0];
    }

    public static void fill(TreePri root, String word) {
        char[] chars = word.toCharArray();
        TreePri cur = root;
        for (char ch : chars) {
            if (cur.next[ch - 'a'] == null) {
                cur.next[ch - 'a'] = new TreePri();
            }
            cur = cur.next[ch - 'a'];
        }
        cur.end = true;
    }

    public static class TreePri {
        public boolean end;
        public TreePri[] next;

        public TreePri() {
            end = false;
            next = new TreePri[26];
        }
    }
}
