package duys_code.day_48;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_02_472_LeetCode
 * @Author Duys
 * @Description 力扣：
 * @Date 2021/10/22 13:45
 **/
public class Code_02_472_LeetCode {
    /**
     * 给一个单词表 string [] words
     * 里面可能有一些单词肯伊拼接在一起，组成一个单词表中已有的单词，返回能拼接成功的哪些单词
     */
    // 毫无疑问。前缀树
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        if (words == null || words.length < 3) {
            return ans;
        }
        // 按照长度。给单词排个序.短的排前面
        Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
        TrieNode trieNode = new TrieNode();
        for (String word : words) {
            char[] str = word.toCharArray();
            if (str.length > 0 && split1(str, 0, trieNode)) {
                ans.add(word);
            } else {
                insert(trieNode, str);
            }
        }
        return ans;
    }

    // 以i结尾的能不能分割
    public static boolean split1(char[] str, int i, TrieNode head) {
        boolean ans = false;
        if (i == str.length) {
            ans = true;
        } else {
            TrieNode cur = head;
            for (int end = i; end < str.length; end++) {
                int path = str[end] - 'a';
                if (cur.nexts[path] == null) {
                    break;
                }
                cur = cur.nexts[path];
                // 当前来到的i之前所有的字符组成的前缀串，在我们的前缀树中有相应的元件。看看i之后的能不能被分割
                // 这里为何要穿head。而不是cur。因为所有的元件公用
                if (cur.end && split1(str, end + 1, head)) {
                    ans = true;
                    break;
                }
            }
        }
        return ans;
    }

    public static void insert(TrieNode head, char[] word) {
        int path = 0;
        for (char w : word) {
            path = w - 'a';
            if (head.nexts[path] == null) {
                head.nexts[path] = new TrieNode();
            }
            head = head.nexts[path];
        }
        // 这一个单词结束，结尾字符标号
        head.end = true;
    }

    public static class TrieNode {
        public boolean end;
        public TrieNode[] nexts;

        public TrieNode() {
            end = false;
            nexts = new TrieNode[26];
        }
    }
}
