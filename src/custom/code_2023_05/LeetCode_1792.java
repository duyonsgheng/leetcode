package custom.code_2023_05;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1792
 * @Author Duys
 * @Description
 * @Date 2023/5/22 13:15
 **/
// 1792. 最大平均通过率
public class LeetCode_1792 {
    //贪心。 根据期望值来排序，优先满足能提升最大的
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.hope() - b.hope() < 0 ? 1 : -1);
        for (int[] p : classes) {
            heap.add(new Node(p[0], p[1]));
        }
        Node cur = null;
        for (int i = 0; i < extraStudents; i++) {
            cur = heap.poll();
            cur.pass++;
            cur.total++;
            heap.add(cur);
        }
        double all = 0;
        while (!heap.isEmpty()) {
            cur = heap.poll();
            all += (cur.pass / cur.total);
        }
        return all / classes.length;
    }

    class Node {
        public double pass;
        public double total;

        public Node(int p, int t) {
            pass = p;
            total = t;
        }

        // 如果增加了一个人，看看通过率提升了多少
        public double hope() {
            return (pass + 1) / (total + 1) - (pass / total);
        }
    }
}
