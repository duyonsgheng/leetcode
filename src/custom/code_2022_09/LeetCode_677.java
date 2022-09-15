package custom.code_2022_09;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_677
 * @Author Duys
 * @Description
 * @Date 2022/9/6 16:43
 **/
public class LeetCode_677 {
    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));

        mapSum.insert("apple", 2);
        System.out.println(mapSum.sum("app"));
    }

    static class MapSum {
        private Node root;
        private Map<String, Integer> map;

        public MapSum() {
            root = new Node();
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            Node cur = root;
            char[] chars = key.toCharArray();
            if (map.containsKey(key)) {
                int pre = map.get(key);
                if (pre == val) {
                    return;
                }
                // 把之前的清除掉
                for (char c : chars) {
                    cur.next[c - 'a'].sum -= pre;
                    cur = cur.next[c - 'a'];
                }
            }
            cur = root;
            for (char c : chars) {
                if (cur.next == null) {
                    cur.next = new Node[26];
                }
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new Node();
                }
                cur.next[c - 'a'].sum += val;
                cur = cur.next[c - 'a'];
            }
            map.put(key, val);
        }

        public int sum(String prefix) {
            char[] chars = prefix.toCharArray();
            Node cur = root;
            for (char c : chars) {
                Node next = cur.next[c - 'a'];
                if (next != null) {
                    cur = cur.next[c - 'a'];
                }
                if (next == null) {
                    return 0;
                }
            }
            return cur.sum;
        }
    }

    static class Node {
        public int sum;
        public Node[] next;

        public Node() {
            next = new Node[26];
        }
    }
}
