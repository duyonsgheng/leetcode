package custom.code_2022_06;

/**
 * @ClassName LeetCode_208
 * @Author Duys
 * @Description
 * @Date 2022/6/10 14:18
 **/
//208. 实现 Trie (前缀树)
public class LeetCode_208 {

    public static class Trie {
        public Trie[] nexts;
        public boolean end;

        public Trie() {
            nexts = new Trie[26];
        }

        public void insert(String word) {
            if (word == null || word.length() < 1) {
                return;
            }
            char[] str = word.toCharArray();
            Trie cur = this;
            for (char ch : str) {
                if (cur.nexts[ch - 'a'] == null) {
                    cur.nexts[ch - 'a'] = new Trie();
                }
                cur = cur.nexts[ch - 'a'];
            }
            cur.end = true;
        }

        public boolean search(String word) {
            if (word == null || word.length() < 1) {
                return false;
            }
            char[] str = word.toCharArray();
            Trie cur = this;
            for (char ch : str) {
                if (cur.nexts[ch - 'a'] == null) {
                    return false;
                }
                cur = cur.nexts[ch - 'a'];
            }
            if (cur.end) {
                return true;
            }
            return false;
        }

        public boolean startsWith(String prefix) {
            if (prefix == null || prefix.length() < 1) {
                return false;
            }
            char[] str = prefix.toCharArray();
            Trie cur = this;
            for (char ch : str) {
                if (cur.nexts[ch - 'a'] == null) {
                    return false;
                }
                cur = cur.nexts[ch - 'a'];
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Trie root = new Trie();
        root.insert("sssa");
        System.out.println(root.startsWith("ss"));
    }
}
