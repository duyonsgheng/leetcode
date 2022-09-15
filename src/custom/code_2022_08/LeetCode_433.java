package custom.code_2022_08;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ClassName LeetCode_433
 * @Author Duys
 * @Description
 * @Date 2022/8/10 16:37
 **/
// 433. 最小基因变化
public class LeetCode_433 {
    char[] items = new char[]{'A', 'C', 'G', 'T'};

    // A * 解法
    // AACCGGTT
    // AAACGGTA
    // "AACCGGTA","AACCGCTA","AAACGGTA"
    public int minMutation(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>();
        for (String str : bank) {
            set.add(str);
        }
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.step - b.step);
        Map<String, Integer> map = new HashMap<>();
        heap.add(new Node(start, end));
        map.put(start, 0);
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            char[] str = cur.s.toCharArray();
            int step = map.get(cur.s);
            // 遍历当前所有的位置
            for (int i = 0; i < 8; i++) {
                // 每个位置都去看看是否可变，抓能匹配的
                for (char c : items) {
                    if (str[i] == c) {
                        continue;
                    }
                    char[] bat = str.clone();
                    bat[i] = c;
                    String curStr = String.valueOf(bat);
                    if (!set.contains(curStr)) {
                        continue;
                    }
                    if (curStr.equals(end)) {
                        return step + 1;
                    }
                    if (!map.containsKey(curStr) || map.get(curStr) > step + 1) {
                        map.put(curStr, step + 1);
                        heap.add(new Node(curStr, end));
                    }
                }
            }
        }
        return -1;
    }

    class Node {
        String s;
        // 算曼哈顿距离
        int step;

        public Node(String source, String target) {
            s = source;
            for (int i = 0; i < 8; i++) {
                if (s.charAt(i) != target.charAt(i)) {
                    step++;
                }
            }
        }
    }
}
