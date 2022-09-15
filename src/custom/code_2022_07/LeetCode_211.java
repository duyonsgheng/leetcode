package custom.code_2022_07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_211
 * @Author Duys
 * @Description
 * @Date 2022/7/6 16:11
 **/
// 211. 添加与搜索单词 - 数据结构设计
public class LeetCode_211 {

    // 反手就是前缀树
    public static class WordDictionary {
        public Node root;

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray();
            Node cur = root;
            for (int i = 0; i < str.length; i++) {
                if (cur.next == null) {
                    cur.next = new Node[26];
                }
                if (cur.next[str[i] - 'a'] == null) {
                    cur.next[str[i] - 'a'] = new Node(str[i]);
                }
                cur = cur.next[str[i] - 'a'];
            }
            cur.isEnd = true;
        }

        public boolean search(String word) {
            if (word == null) {
                return false;
            }
            char[] str = word.toCharArray();
            return find(root.next, 0, str);
        }

        public boolean find(Node[] curNodes, int index, char[] ca) {

            // 没有下文了
            if (curNodes == null) {
                return false;
            }
            // 都到了越界的位置，说明没找到啊
            if (index == ca.length) {
                return false;
            }
            char cur = ca[index];
            // 最后一个
            if (index == ca.length - 1) {
                if (cur == '.') {
                    for (int i = 0; i < curNodes.length; i++) {
                        if (curNodes[i] == null) {
                            continue;
                        }
                        if (curNodes[i].isEnd) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    return curNodes[cur - 'a'] == null ? false : curNodes[cur - 'a'].isEnd;
                }
            } else {
                if (cur == '.') {
                    boolean next = false;
                    for (int i = 0; i < curNodes.length; i++) {
                        if (curNodes[i] == null) {
                            continue;
                        }
                        next = find(curNodes[i].next, index + 1, ca);
                        if (next) {
                            return next;
                        }
                    }
                } else {
                    if (curNodes[cur - 'a'] == null) {
                        return false;
                    }
                    return find(curNodes[cur - 'a'].next, index + 1, ca);
                }
            }
            return false;
        }
    }

    public static class Node {
        public char cur;
        public Node[] next;
        public boolean isEnd;

        public Node() {
            next = new Node[26];
        }

        public Node(char c) {
            cur = c;
        }
    }

    public static void main(String[] args) {
        // ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
        //[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
        WordDictionary wordDictionary = new WordDictionary();
       /* wordDictionary.addWord("");
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        System.out.println(wordDictionary.search("mad"));
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));*/
        System.out.println(wordDictionary.search("b"));
    }
}
