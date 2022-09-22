package custom.code_2022_09;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_791
 * @Author Duys
 * @Description
 * @Date 2022/9/20 15:31
 **/
// 791. 自定义字符串排序
public class LeetCode_791 {
    public static String customSortString(String order, String s) {
        char[] source = order.toCharArray();
        Map<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < source.length; i++) {
            indexMap.put(source[i], i);
        }
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.index - b.index);
        char[] arr = s.toCharArray();
        for (char c : arr) {
            queue.offer(new Node(c, indexMap.getOrDefault(c, 100)));
        }
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            builder.append(queue.poll().c);
        }
        return builder.toString();
    }

    static class Node {
        public char c;
        public int index;

        public Node(char _c, int _index) {
            c = _c;
            index = _index;
        }
    }

    public static void main(String[] args) {
        String order = "cbafg", s = "abcd";
        System.out.println(customSortString(order,s));
    }
}
