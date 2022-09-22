package custom.code_2022_09;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @ClassName LeetCode_792
 * @Author Duys
 * @Description
 * @Date 2022/9/20 15:59
 **/
// 792. 匹配子序列的单词数
public class LeetCode_792 {


    public int numMatchingSubseq(String s, String[] words) {
        int ans = 0;
        List<Node>[] heads = new List[26];
        Arrays.setAll(heads, o -> new ArrayList<>());

        for (String word : words)
            heads[word.charAt(0) - 'a'].add(new Node(word, 0));
        // s = 'dcaog' w =[dog,cat,cop]
        for (char c : s.toCharArray()) {
            List<Node> last = heads[c - 'a'];
            heads[c - 'a'] = new ArrayList<>();
            for (Node node : last) {
                node.index++;
                if (node.index == node.word.length()) {
                    ans++;
                } else {
                    heads[node.word.charAt(node.index) - 'a'].add(node);
                }
            }
            last = null;
        }
        return ans;
    }

    class Node {
        String word;
        int index;

        public Node(String w, int i) {
            word = w;
            index = i;
        }
    }

    public int numMatchingSubseq1(String s, String[] words) {
        Tire root = new Tire();
        for (int i = 0; i < words.length; i++)
            root.add(words[i], i);
        return root.find(s);
    }

    class Tire {
        Tire[] nexts = new Tire[26];
        Set<Integer> ids = new HashSet<>();

        public void add(String w, int id) {
            Tire cur = this;
            int n = w.length();
            for (int i = 0; i < n; i++) {
                int index = w.charAt(i) - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Tire();
                }
                cur = cur.nexts[index];
            }
            cur.ids.add(id);
        }

        public int find(String w) {
            TreeSet<Integer>[] items = new TreeSet[26];
            Arrays.setAll(items, item -> new TreeSet<>());
            for (int i = 0; i < w.length(); i++) {
                int index = w.charAt(i) - 'a';
                items[index].add(i);
            }
            return find(this, 0, items).size();
        }

        private Set<Integer> find(Tire cur, int index, TreeSet<Integer>[] items) {
            Set<Integer> set = new TreeSet<>(cur.ids);
            for (int i = 0; i < 26; i++) {
                if (!items[i].isEmpty() && cur.nexts[i] != null) {
                    Integer ceiling = items[i].ceiling(index);
                    if (ceiling != null) {
                        set.addAll(find(cur.nexts[index], ceiling + 1, items));
                    }
                }
            }
            return set;
        }
    }
}
