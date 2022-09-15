package custom.code_2022_09;

/**
 * @ClassName LeetCode_676
 * @Author Duys
 * @Description
 * @Date 2022/9/6 15:16
 **/
//
public class LeetCode_676 {
    public static void main(String[] args) {

    }

    class MagicDictionary {
        private Node root;

        public MagicDictionary() {
            root = new Node();
        }

        public void buildDict(String[] dictionary) {
            for (String str : dictionary) {
                init(str);
            }
        }

        public boolean search(String searchWord) {
            char[] source = searchWord.toCharArray();
            Node cur = root;
            return process(source, 0, cur, false);
        }

        private boolean process(char[] arr, int index, Node head, boolean pre) {
            if (index == arr.length) {
                return pre && head.end;
            }
            int next = arr[index] - 'a';
            if (head.next[next] != null) {
                if (process(arr, index + 1, head.next[next], pre)) {
                    return true;
                }
            }
            // 为空了
            // 之前没改过
            if (!pre) {
                for (int i = 0; i < 26; ++i) {
                    if (i != next && head.next[i] != null) {
                        if (process(arr, index + 1, head.next[i], true)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private void init(String str) {
            Node cur = root;
            char[] arr = str.toCharArray();
            for (char c : arr) {
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new Node();
                }
                cur = cur.next[c - 'a'];
            }
            cur.end = true;
        }
    }

    class Node {
        public boolean end;
        public Node[] next;

        public Node() {
            next = new Node[26];
        }
    }
}
