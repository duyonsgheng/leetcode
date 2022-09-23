package custom.code_2022_09;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_820
 * @Author Duys
 * @Description
 * @Date 2022/9/23 14:37
 **/
// 820. 单词的压缩编码
public class LeetCode_820 {
    // 后续字典
    // time me bell
    public static int minimumLengthEncoding(String[] words) {
        Node root = new Node();
        Map<Node, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Node node = root;
            for (int j = word.length() - 1; j >= 0; j--) {
                node = node.get(word.charAt(j));
            }
            map.put(node, i);
        }
        int ans = 0;
        for (Node node : map.keySet()) {
            if (node.count == 0) {
                ans += words[map.get(node)].length() + 1;
            }
        }
        return ans;
    }

    static class Node {
        int count;
        Node[] nexts;

        public Node() {
            nexts = new Node[26];
            count = 0;
        }

        public Node get(char c) {
            if (nexts[c - 'a'] == null) {
                nexts[c - 'a'] = new Node();
                count++;
            }
            return nexts[c - 'a'];
        }
    }

    public static void main(String[] args) {
        String[] words = {"time", "me", "bell"};
        minimumLengthEncoding(words);
    }
}
