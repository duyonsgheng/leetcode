package custom.code_2022_09;

/**
 * @ClassName LeetCode_809
 * @Author Duys
 * @Description
 * @Date 2022/9/22 17:28
 **/
// 809. 情感丰富的文字
public class LeetCode_809 {
    public static void main(String[] args) {
        String s = "heeellooo";
        String[] w = {"hello", "hi", "helo"};
        System.out.println(expressiveWords(s, w));
    }

    public static int expressiveWords(String s, String[] words) {
        Trie root = new Trie();
        build(root, s);
        int ans = 0;
        for (String w : words) {
            ans += matcher(root, w) ? 1 : 0;
        }
        return ans;
    }

    public static boolean matcher(Trie root, String w) {
        Trie node = root;
        char[] arr = w.toCharArray();
        int index = 0;
        int n = arr.length;
        while (index < n) {
            char cur = arr[index];
            int r = index;
            while (r < n && arr[r] == cur) {
                r++;
            }
            int size = r - index;
            // 如果不包含了
            // 如果字符不一致
            // 如果字符数 <=2 那么字符数量必须得两边一致
            // 如果原字符数量大于了2 并且我当前字符数量大于了原字符数量
            if (node.size == 0 || node.c != cur
                    || (node.size <= 2 && node.size != size)
                    || (node.size > 2 && size > node.size)) {
                return false;
            }
            node = node.next;
            index = r;
        }
        // 是否来到了最后，意思就是当前单词得词序必须要和原字符保持一致
        return node.size == 0 ? true : false;
    }

    public static void build(Trie root, String s) {
        Trie node = root;
        char[] chars = s.toCharArray();
        int index = 0;
        int n = chars.length;
        while (index < n) {
            char cur = chars[index];
            int r = index;
            // 相同的几个
            while (r < n && chars[r] == cur) {
                r++;
            }
            node.c = cur;
            node.size = r - index;
            node.next = new Trie();
            node = node.next;
            index = r;
        }
    }

    static class Trie {
        public char c;
        public Trie next;
        public int size;
    }
}
