package week.week_2023_07_01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName Code_02_LeetCode_30
 * @date 2023年07月06日
 */
// https://leetcode.cn/problems/substring-with-concatenation-of-all-words/
public class Code_02_LeetCode_30 {
    // 字符串hash + 滑动窗口
    public static int BASE = 499;

    // 计算一个字符串的哈希值
    public static long hashValue(String str) {
        if (str.equals("")) {
            return 0;
        }
        int n = str.length();
        long ans = str.charAt(0) - 'a' + 1;
        for (int j = 1; j < n; j++) {
            ans = ans * BASE + str.charAt(j) - 'a' + 1;
        }
        return ans;
    }

    // 字符串最大长度
    // 以下内容看字符串哈希的内容
    public static int MAXN = 10001;

    public static long[] pow = new long[MAXN];

    static {
        pow[0] = 1;
        for (int j = 1; j < MAXN; j++) {
            pow[j] = pow[j - 1] * BASE;
        }
    }

    public static long[] hash = new long[MAXN];

    public static void buildHash(String str) {
        hash[0] = str.charAt(0) - 'a' + 1;
        for (int j = 1; j < str.length(); j++) {
            hash[j] = hash[j - 1] * BASE + str.charAt(j) - 'a' + 1;
        }
    }

    // 范围是[l,r)，左闭右开
    public static long hashValue(int l, int r) {
        long ans = hash[r - 1];
        ans -= l == 0 ? 0 : (hash[l - 1] * pow[r - l]);
        return ans;
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return ans;
        }
        // 单词记录，方便记账
        HashMap<Long, Integer> map = new HashMap<>();
        for (String word : words) {
            long v = hashValue(word);
            map.put(v, map.getOrDefault(v, 0) + 1);
        }
        // 字符串hash
        buildHash(s);

        // 下面开始窗口，
        int n = s.length();
        int wordLen = words[0].length();
        int wordNum = words.length;
        int allLen = wordLen * wordNum;
        HashMap<Long, Integer> window = new HashMap<>();
        for (int init = 0; init < wordLen && init + allLen <= n; init++) {
            // 就是这样子的窗口
            // [0...5) [5...10) [10...15) ...
            // [1...6) [6...11) [11...16) ...
            // [2...7) [7...12) [12...17) ...
            // 欠债的总数
            int debt = wordNum;
            for (int l = init, r = init + wordLen, part = 0; part < wordNum; l += wordLen, r += wordLen, part++) {
                long cur = hashValue(l, r);
                window.put(cur, window.getOrDefault(cur, 0) + 1);
                // 当前窗口内此字符的个数是小于等于源单词中的数量，则当前窗口内是可以满足的
                if (window.get(cur) <= map.getOrDefault(cur, 0)) {
                    debt--;
                }
            }
            if (debt == 0) {
                ans.add(init);
            }
            // 窗口要缩进和扩展了
            // l1...r1 是要出窗口的
            // l2...r2 是要进窗口的
            for (int l1 = init, r1 = init + wordLen, l2 = init + allLen, r2 = init + allLen + wordLen;
                 r2 <= n; l1 += wordLen, r1 += wordLen, l2 += wordLen, r2 += wordLen) {
                long out = hashValue(l1, r1);
                long in = hashValue(l2, r2);
                window.put(out, window.get(out) - 1);
                if (window.get(out) < map.getOrDefault(out, 0)) {
                    debt++; // 需要还一个
                }
                window.put(in, window.getOrDefault(in, 0) + 1);
                if (window.get(in) <= map.getOrDefault(in, 0)) {
                    debt--;
                }
                if (debt == 0) {
                    ans.add(r1);
                }
            }
            // 本次的窗口需要清除了
            window.clear();
        }
        return ans;
    }
}
