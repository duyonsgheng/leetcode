package duys_code.day_51;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * @ClassName Code_03_642_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/design-search-autocomplete-system/
 * @Date 2021/11/5 17:12
 **/
public class Code_03_642_LeetCode {
    /**
     * 设计搜索框自动补全系统
     * 前缀树
     */
    static class AutocompleteSystem {
        public final int top = 3;// 题目要求只需要前三个
        public TrieNode root = new TrieNode(null, "");

        // 某一个前缀树的节点，当前前缀节点上挂了哪些单词
        public HashMap<TrieNode, TreeSet<WordCount>> nodeRankMap = new HashMap<>();

        // 字符串对应的次数
        public HashMap<String, WordCount> wordCountMap = new HashMap<>();

        //
        public String path;
        public TrieNode cur;

        // ' ' -> 0
        // 'a' -> 1
        // 'b' -> 2
        // ...
        // 'z' -> 26
        //  '`'  a b  .. z
        private int f(char c) {
            return c == ' ' ? 0 : (c - '`');
        }

        public AutocompleteSystem(String[] sentences, int[] times) {
            path = "";
            cur = root;
            for (int i = 0; i < sentences.length; i++) {
                String word = sentences[i];
                int count = times[i];
                WordCount wc = new WordCount(word, count - 1);
                wordCountMap.put(word, wc);
                for (char c : word.toCharArray()) {
                    input(c);
                }
                input('#');
            }
        }

        // 之前已经有一些历史了
        // 当前输入c字符
        // 请根据之前得历史,然后沿着c字符继续
        // path 之前得历史
        // cur 当前滑到了哪一个前缀树节点
        public List<String> input(char c) {
            List<String> ans = new ArrayList<>();
            // 不是本次得结束字符串标志位
            if (c != '#') {
                path += c;
                // 转化成数组下标,去前缀树中找
                int index = f(c);
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new TrieNode(cur, path);
                }
                cur = cur.nexts[index];
                if (!nodeRankMap.containsKey(cur)) {
                    nodeRankMap.put(cur, new TreeSet<>());
                }
                int k = 0;
                for (WordCount wordCount : nodeRankMap.get(cur)) {
                    if (k == top) {
                        break;
                    }
                    ans.add(wordCount.word);
                    k++;
                }
            }
            if (c == '#' && !path.equals("")) {
                if (!wordCountMap.containsKey(path)) {
                    wordCountMap.put(path, new WordCount(path, 0));
                }
                WordCount old = wordCountMap.get(path);
                WordCount ne = new WordCount(path, old.count + 1);
                while (cur != root) {
                    nodeRankMap.get(cur).remove(old);
                    nodeRankMap.get(cur).add(ne);
                    cur = cur.father;
                }
                // 本次输入的是 # 表示依次输入的结束,要清除历史
                path = "";
            }
            return ans;
        }


        public class TrieNode {
            public TrieNode father;
            public String path;
            public TrieNode[] nexts;

            public TrieNode(TrieNode f, String pa) {
                father = f;
                path = pa;
                // 有一个 " "
                nexts = new TrieNode[27];
            }
        }

        // 单词统计
        public class WordCount implements Comparable<WordCount> {
            public String word;
            public int count;

            public WordCount(String w, int c) {
                word = w;
                count = c;
            }

            @Override
            public int compareTo(WordCount o) {
                return count != o.count ? (o.count - count) : word.compareTo(o.word);
            }
        }

    }
}
