package custom.code_2022_11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1268
 * @Author Duys
 * @Description
 * @Date 2022/11/30 14:37
 **/
// 1268. 搜索推荐系统
public class LeetCode_1268 {

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = new TrieNode();
        for (String product : products) {
            build(product, root);
        }
        char[] arr = searchWord.toCharArray();
        int n = arr.length;
        List<List<String>> ans = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buffer.append(arr[i]);
            ans.add(find(buffer, root));
        }
        return ans;
    }

    public List<String> find(StringBuffer buffer, TrieNode root) {
        char[] chars = buffer.toString().toCharArray();
        List<String> ans = new ArrayList<>();
        TrieNode cur = root;
        PriorityQueue<String> queue = new PriorityQueue<>((a, b) -> a.compareTo(b));
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            TrieNode next = cur.nexts[index];
            if (next == null) {// 如果都还没到当前搜的尾巴，没了，说明找不到了
                return ans;
            }
            cur = next;
        }
        if (cur == null) {
            return ans;
        }
        if (cur.end) {
            queue.add(cur.str);
        }
        TrieNode[] nexts = cur.nexts;
        for (TrieNode next : nexts) {
            process(next, queue);
            if (queue.size() >= 3) {
                break;
            }
        }
        int i = 3;
        while (i > 0 && !queue.isEmpty()) {
            ans.add(queue.poll());
            i--;
        }
        return ans;
    }

    public void process(TrieNode node, PriorityQueue<String> queue) {
        if (node == null) {
            return;
        }
        if (node.end) {
            queue.add(node.str);
        }
        TrieNode[] nexts = node.nexts;
        if (nexts == null) {
            return;
        }
        for (TrieNode next : nexts) {
            process(next, queue);
        }
    }

    public void build(String pro, TrieNode root) {
        TrieNode cur = root;
        for (char c : pro.toCharArray()) {
            int index = c - 'a';
            if (cur.nexts[index] == null) {
                cur.nexts[index] = new TrieNode();
            }
            cur = cur.nexts[index];
        }
        cur.end = true;
        cur.str = pro;
    }

    class TrieNode {
        String str;
        boolean end;
        TrieNode[] nexts;

        public TrieNode() {
            nexts = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        LeetCode_1268 lc = new LeetCode_1268();
        String products[] = {"mobile", "mouse", "moneypot", "monitor", "mousepad"}, searchWord = "mouse";
        List<List<String>> list = lc.suggestedProducts(products, searchWord);
        System.out.println(list);
    }

    public List<List<String>> suggestedProducts1(String[] products, String searchWord) {
        Node root = new Node();
        for (int i = 0; i < products.length; i++) {
            String p = products[i];
            Node node = root;
            for (char c : p.toCharArray()) {
                node = node.computeIfAbsent(c, k -> new Node());
                if (node.queue.isEmpty() || node.queue.size() < 3) {
                    node.queue.add(p);
                } else if (node.queue.peek().compareTo(p) > 0) {
                    node.queue.poll();
                    node.queue.add(p);
                }
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (char s : searchWord.toCharArray()) {
            if (root == null || !root.containsKey(s)) {
                ans.add(new ArrayList<>());
                root = null;
            } else {
                root = root.get(s);
                PriorityQueue<String> queue = root.queue;
                List<String> add = new ArrayList<>();
                while (!queue.isEmpty()) {
                    add.add(queue.poll());
                }
                Collections.reverse(add);
                ans.add(add);
            }
        }
        return ans;
    }

    class Node extends HashMap<Character, Node> {
        PriorityQueue<String> queue = new PriorityQueue<>((a, b) -> b.compareTo(a));
    }
}
