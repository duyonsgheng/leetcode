package hope.bfs;

import java.util.*;

/**
 * @author Mr.Du
 * @ClassName Code02_StickersToSpellWord
 * @date 2023年11月22日 11:10
 */
// 贴纸拼词
// 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
// 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们
// 如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
// 返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1
// 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的
// 并且 target 被选择为两个随机单词的连接。
// 测试链接 : https://leetcode.cn/problems/stickers-to-spell-word/
public class Code02_StickersToSpellWord {
    public static int MAXN = 401;
    public static String[] queue = new String[MAXN];
    public static int l, r;
    public static List<List<String>> graph = new ArrayList<>();
    public static Set<String> visited = new HashSet<>();

    static {
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public static int minStickers(String[] stickers, String target) {
        for (int i = 0; i < 26; i++) {
            graph.get(i).clear();
        }
        visited.clear();
        for (String word : stickers) {
            word = sort(word);
            for (int i = 0; i < word.length(); i++) {
                if (i == 0 || word.charAt(i) != word.charAt(i - 1)) {
                    graph.get(word.charAt(i) - 'a').add(word);
                }
            }
        }
        target = sort(target);
        visited.add(target);
        l = r = 0;
        queue[r++] = target;
        int level = 1;
        while (l < r) {
            int size = r - l;
            for (int i = 0; i < size; i++) {
                String cur = queue[l++];
                for (String s : graph.get(cur.charAt(0) - 'a')) {
                    String next = next(cur, s);
                    if (next.equals("")) {
                        return level;
                    } else if (!visited.contains(next)) {
                        visited.add(next);
                        queue[r++] = next;
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public static String sort(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return String.valueOf(arr);
    }

    public static String next(String t, String s) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0, j = 0; i < t.length(); ) {
            if (j == s.length()) { // 到了源字符串的末尾了，直接把目标字符串拷贝
                buffer.append(t.charAt(i++));
            } else {
                // 如果目标串的字符是小于源字符的，拷贝目标字符
                if (t.charAt(i) < s.charAt(j)) {
                    buffer.append(t.charAt(i++));
                } else if (t.charAt(i) > s.charAt(j)) {
                    // 如果目标字符大于了源字符，那么源字符往下
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
        }
        return buffer.toString();
    }
}
