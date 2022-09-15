package custom.code_2022_08;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_451
 * @Author Duys
 * @Description
 * @Date 2022/8/12 11:07
 **/
// 451. 根据字符出现频率排序
public class LeetCode_451 {

    public static String frequencySort(String s) {
        if (s == null || s.equals("")) {
            return "";
        }
        char[] str = s.toCharArray();
        Map<Character, Integer> count = new HashMap<>();
        for (char c : str) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> b.count - a.count);
        for (char c : count.keySet()) {
            heap.add(new Node(c, count.get(c)));
        }
        StringBuilder builder = new StringBuilder();
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            for (int i = 0; i < cur.count; i++) {
                builder.append(cur.c);
            }
        }
        return builder.toString();
    }

    static class Node {
        char c;
        int count;

        public Node(char a, int t) {
            c = a;
            count = t;
        }
    }

    public static void main(String[] args) {
        String str = "tree";
        System.out.println(frequencySort(str));
    }
}
