package week.week_2023_01_01;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName Code_03_LeetCode_857
 * @Author Duys
 * @Description
 * @Date 2023/1/5 10:04
 **/
// 857. 雇佣 K 名工人的最低成本
public class Code_03_LeetCode_857 {
    // 分析，根据题意我们可以知道，一定要满足两个条件。
    // 第一个条件就卡住了，我们不能选择能力非常强，但是要价比较低的，因为这样子就不能满足第一个限制，
    // 所以我们需要得到一个标准来计算
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(wage[i], quality[i]);
        }
        // 根据垃圾指数排序，越小的再前面，垃圾指数越小，性价比越高。但是不能选择性价比较高的作为基准
        Arrays.sort(nodes, (a, b) -> a.bill <= b.bill ? -1 : 1);
        // 搞一个大根堆-门槛堆，谁小谁就可以进来
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        double ans = Double.MAX_VALUE;
        for (int i = 0, sum = 0; i < n; i++) {
            int cur = nodes[i].quality;
            if (queue.size() < k) { // 不足k个人
                sum += cur;
                queue.add(cur);
                if (queue.size() == k) {
                    // 满了k个算一下答案
                    ans = Math.min(ans, sum * nodes[i].bill);
                }
            } else {
                // 当前有k个人了
                // 当前员工的能力比堆顶还要小。那么当前员工进去
                if (queue.peek() > cur) {
                    sum += cur - queue.poll();
                    queue.add(cur);
                    ans = Math.min(ans, sum * nodes[i].bill);
                }
            }
        }
        return ans;
    }

    class Node {
        public double bill; // 这是垃圾指数，就是指标
        public int quality;

        public Node(int w, int q) {
            bill = (double) w / (double) q;
            quality = q;
        }
    }
}
