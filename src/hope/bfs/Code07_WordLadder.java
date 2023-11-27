package hope.bfs;

import java.util.HashSet;
import java.util.List;

// 单词接龙
// 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列
// 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk ：
// 每一对相邻的单词只差一个字母。
// 对于 1 <= i <= k 时，每个 si 都在 wordList 中
// 注意， beginWord 不需要在 wordList 中。sk == endWord
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList
// 返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目
// 如果不存在这样的转换序列，返回 0 。
// 测试链接 : https://leetcode.cn/problems/word-ladder/
public class Code07_WordLadder {
    // 双向广搜
    // 两边同时bfs
    // 然后汇总
    public static int ladderLength(String begin, String end, List<String> wordList) {
        // 总的词表
        HashSet<String> dist = new HashSet<>();
        if (!wordList.contains(end)) {
            return 0;
        }
        // 小的一端
        HashSet<String> smallLevel = new HashSet<>();
        // 大的一端
        HashSet<String> bigLevel = new HashSet<>();
        // 由数量少的扩展的下一层
        HashSet<String> nextLevel = new HashSet<>();
        smallLevel.add(begin);
        bigLevel.add(begin);
        for (int len = 2; !smallLevel.isEmpty(); len++) {
            for (String w : smallLevel) {
                char[] arr = w.toCharArray();
                for (int h = 0; h < arr.length; h++) {
                    char old = arr[h];
                    for (char n = 'a'; n <= 'z'; n++) {
                        if (n != old) {
                            arr[h] = n;
                            String next = String.valueOf(arr);
                            if (bigLevel.contains(next)) {
                                return len;
                            }
                            if (dist.contains(next)) {
                                dist.remove(next);
                                nextLevel.add(next);
                            }
                        }
                    }
                    arr[h] = old;
                }
            }
            HashSet<String> tmp = smallLevel;
            if (nextLevel.size() <= bigLevel.size()) {
                smallLevel = nextLevel;
            } else {
                smallLevel = bigLevel;
                bigLevel = nextLevel;
            }
            nextLevel = tmp;
            nextLevel.clear();
        }
        return 0;
    }

}
