package custom.code_2022_09;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_720
 * @Author Duys
 * @Description
 * @Date 2022/9/13 16:32
 **/
// 720. 词典中最长的单词
public class LeetCode_720 {
    public String longestWord(String[] words) {
        Node root = new Node();
        for (String str : words)
            build(root, str);
        String ans = "";
        for (String str : words) {
            if (!search(root, str)) {
                continue;
            }
            if (str.length() > ans.length()
                    || (str.length() == ans.length() && str.compareTo(ans) < 0)) {
                ans = str;
            }
        }
        return ans;
    }


    private void build(Node root, String str) {
        Node cur = root;
        char[] arr = str.toCharArray();
        for (char c : arr) {
            int next = c - 'a';
            if (cur.nexts == null) {
                cur.nexts = new Node[26];
            }
            if (cur.nexts[next] == null) {
                cur.nexts[next] = new Node();
            }
            cur = cur.nexts[next];
        }
        cur.end = true;
    }

    private boolean search(Node root, String word) {
        char[] arr = word.toCharArray();
        Node cur = root;
        for (char c : arr) {
            int next = c - 'a';
            // 如果每一个单词都不是结尾的话，
            if (cur.nexts[next] == null || !cur.nexts[next].end) {
                return false;
            }
            cur = cur.nexts[next];
        }
        return cur != null && cur.end;
    }

    class Node {
        private boolean end;
        private Node[] nexts;

        public Node() {
            nexts = new Node[26];
        }

    }
}
