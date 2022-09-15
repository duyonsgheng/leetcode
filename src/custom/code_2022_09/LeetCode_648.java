package custom.code_2022_09;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_648
 * @Author Duys
 * @Description
 * @Date 2022/9/1 18:24
 **/
// 648. 单词替换
public class LeetCode_648 {
    public static void main(String[] args) {
        String[] dictionary = {"cat", "bat", "rat"};
        List<String> list = Arrays.asList(dictionary);
        String sentence = "the cattle was rattled by the battery";
        System.out.println(replaceWords(list, sentence));
    }

    // 前缀树
    public static String replaceWords(List<String> dictionary, String sentence) {
        if (dictionary == null || dictionary.size() <= 0 || sentence == null || sentence.length() <= 0) {
            return "";
        }
        // 构建好前缀树
        Node root = new Node();
        for (String str : dictionary) {
            if (str == null || "".equals(str)) {
                continue;
            }
            char[] chars = str.toCharArray();
            Node curNode = root;
            for (char c : chars) {
                if (curNode.next == null) {
                    curNode.next = new Node[26];
                }
                if (curNode.next[c - 'a'] == null) {
                    curNode.next[c - 'a'] = new Node(c);
                }
                curNode = curNode.next[c - 'a'];
            }
            curNode.isEnd = true;
        }
        String[] arr = sentence.split(" ");
        StringBuilder builder = new StringBuilder();
        Node next = null;
        for (String cur : arr) {
            next = root;
            builder.append(query(next, cur)).append(" ");
        }
        return builder.toString().trim();
    }

    public static String query(Node root, String s) {
        Node cur = root;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            Node node = cur.next[index];
            if (node == null) {
                break;
            }
            if (node.isEnd) {
                return s.substring(0, i + 1);
            }
            cur = cur.next[index];
        }
        return s;
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
}
