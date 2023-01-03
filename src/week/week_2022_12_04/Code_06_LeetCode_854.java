package week.week_2022_12_04;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ClassName Code_06_LeetCode_854
 * @Author Duys
 * @Description
 * @Date 2023/1/3 15:10
 **/
// 854. 相似度为 K 的字符串
public class Code_06_LeetCode_854 {

    public static class Node {
        public int cost; // 代价，已经换了几回了！
        public int guess;// 猜测还要换几回，能变对！ AStart 算法的核心
        public int where;// 有必须去比对的下标，左边不再换了！
        public String str; // 当前的字符

        public Node(int r, int g, int i, String s) {
            cost = r;
            guess = g;
            where = i;
            str = s;
        }
    }

    public int kSimilarity(String s1, String s2) {
        int n = s1.length();
        // 通过AStart给出一个指导价值
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> (a.cost + a.guess) - (b.cost + b.guess));
        Set<String> set = new HashSet<>();
        queue.add(new Node(0, 0, 0, s1));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (set.contains(cur.str)) {
                continue;
            }
            if (cur.str.equals(s2)) {
                return cur.cost;
            }
            set.add(cur.str);
            int firstDiff = cur.where;
            // 找到两个字符串不等的位置
            while (cur.str.charAt(firstDiff) == s2.charAt(firstDiff)) {
                firstDiff++;
            }
            char[] arr = cur.str.toCharArray();
            for (int i = firstDiff + 1; i < n; i++) {
                // 从不等的位置开始，cur的i位置和s2的i位置字符不一样，但是和s2的firstDiff位置一样，那么可以交换
                if (arr[i] == s2.charAt(firstDiff) && arr[i] != s2.charAt(i)) {
                    swap(arr, i, firstDiff);
                    add(String.valueOf(arr), s2, cur.cost + 1, firstDiff + 1, queue, set);
                    swap(arr, i, firstDiff);
                }
            }
        }
        return -1;
    }

    public void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void add(String add, String s2, int cost, int index, PriorityQueue<Node> queue, Set<String> set) {
        if (set.contains(add)) {
            return;
        }
        queue.add(new Node(cost, evaluate(add, s2, index), index, add));
    }

    public int evaluate(String s1, String s2, int index) {
        int diff = 0;
        for (int i = index; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }
        return (diff + 1) / 2;
    }
}
